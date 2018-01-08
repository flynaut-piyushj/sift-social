package com.example.dell.socialsift.utils.networking;

import android.content.Context;

import com.example.dell.socialsift.beans.LoginBean;
import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.DatabaseHandler;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;

import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClientWithToken {


    private static Retrofit retrofit = null;
    private SingletonUtil singletonUtil = SingletonUtil.getSingletonConfigInstance();
    private static String TAG = ApiClientWithToken.class.getSimpleName();


    public static Retrofit getClient(final Context context) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();



        final DatabaseHandler databaseHandler = new DatabaseHandler(context);
        final String authenticationToken = databaseHandler.getTokenInfo().getTokenType() + " " + databaseHandler.getTokenInfo().getAccessToken();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", authenticationToken)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });



        httpClient.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {

                if(responseCount(response) >= 2) {

                    return null;
                }
//                synchronized (httpClient) {
                if (response.code() == 401) {
                    refreshToken(context,databaseHandler.getTokenInfo().getRefreshToken());

                }
//                }
                final String authenticationToken2 = databaseHandler.getTokenInfo().getTokenType() + " " + databaseHandler.getTokenInfo().getAccessToken();
                System.out.println("NEW TOKEN==>" + authenticationToken2);
                return response.request().newBuilder()
                        .header("Authorization",authenticationToken2)
                        .build();
            }

            public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                // Null indicates no attempt to authenticate.
                return null;
            }

        });

//        set logging interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        OkHttpClient client = httpClient.readTimeout(25, TimeUnit.SECONDS)
                .connectTimeout(25, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }




    public static void refreshToken(final Context context, String refreshToken){



        final Retrofit retrofit = ApiClient.getClient(context);
        ApiInterface apiService = retrofit.create(ApiInterface.class);
        Call<LoginBean> call = apiService.getRefreshToken("refresh_token", refreshToken);
        try {
            LoginBean bean = call.execute().body();
            DatabaseHandler db = new DatabaseHandler(context);
            db.updateTokennfo(bean.getTokenType(), bean.getAccessToken(),refreshToken);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
