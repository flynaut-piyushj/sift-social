package com.example.dell.socialsift.utils.networking;

import android.content.Context;

import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.DatabaseHandler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DELL on 10/3/2017.
 */

public class OldWithTokenClass {


    private static Retrofit retrofit = null;

    private static String TAG = ApiClientWithToken.class.getSimpleName();


    public static Retrofit getClient(final Context context) {


//        final SessionManager sessionManager = new SessionManager(context);

        /*set header */
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        DatabaseHandler databaseHandler = new DatabaseHandler(context);
//        final String a = "bearer 23316686-941e-4aa3-8863-45b3e36689af";
        final String authenticationToken = databaseHandler.getTokenInfo().getTokenType() + " " + databaseHandler.getTokenInfo().getAccessToken();
        System.out.println("authenticationToken  " + authenticationToken );
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

//        set logging interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        OkHttpClient client = httpClient.readTimeout(25, TimeUnit.SECONDS)
                .connectTimeout(25, TimeUnit.SECONDS)
                .build();

//        if (retrofit == null) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
//        }
        return retrofit;
    }
}
