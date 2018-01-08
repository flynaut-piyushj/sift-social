package com.example.dell.socialsift.ui.loginUI;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.beans.LoginBean;
import com.example.dell.socialsift.beans.common.ErrorMessageResponse;
import com.example.dell.socialsift.beans.masterBean.MasterDataBean;
import com.example.dell.socialsift.beans.profileBean.GetUserDashboardBean;
import com.example.dell.socialsift.ui.DashBoardActivity;
import com.example.dell.socialsift.ui.ParentActivity;
import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.DatabaseHandler;
import com.example.dell.socialsift.utils.commonUtils.SessionManager;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;
import com.example.dell.socialsift.utils.networking.ApiClient;
import com.example.dell.socialsift.utils.networking.ApiClientWithToken;
import com.example.dell.socialsift.utils.networking.ApiClientWithoutToken;
import com.example.dell.socialsift.utils.networking.ApiInterface;
import com.example.dell.socialsift.utils.networking.ErrorUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.view.View.GONE;


/**
 * Created by ${Akshata} on ${10/17/2016}.
 */
public class LoginActivity extends ParentActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnSignin;
    private Button btnSignup;
    private TextView txtForgotPassword;
    private SingletonUtil singletonUtil;
    private String TAG = "LoginActivity";
    private DatabaseHandler databaseHandler;
    private RelativeLayout parentLayout;
    private SessionManager sessionManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();
        databaseHandler.deleteTokenInfo();
        if (databaseHandler.checkCountOfCity() == 0)
            callToMasterData();
    }


    public void init() {

        btnSignin = (Button) findViewById(R.id.buttonsignin);
        btnSignin.setOnClickListener(this);
        btnSignup = (Button) findViewById(R.id.buttonsignup);
        btnSignup.setOnClickListener(this);
        parentLayout = (RelativeLayout) findViewById(R.id.parentLayout);
        singletonUtil = SingletonUtil.getSingletonConfigInstance();
        databaseHandler = new DatabaseHandler(getApplicationContext());
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
//        databaseHandler.deleteTokenInfo();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        sessionManager = new SessionManager(getApplicationContext());
    }


    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonsignin:
                attemptLogin();
//                Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                startActivity(intent);
//                finish();


                break;

            case R.id.buttonsignup:
                Intent register_intent = new Intent(getApplicationContext(), RegistrationActivity_1.class);
                startActivity(register_intent);
                break;


        }
    }

    public void callToMasterData() {
        final Retrofit retrofit = ApiClientWithoutToken.getClient();
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<MasterDataBean> call = apiService.getMasterData();

        call.enqueue(new Callback<MasterDataBean>() {
                         @Override
                         public void onResponse(Call<MasterDataBean> call, Response<MasterDataBean> response) {
                             Log.d(TAG, "response=" + response.code());
//                             progressBar.setVisibility(View.GONE);
                             if (response.isSuccessful()) {
                                 for (int i = 0; i < response.body().getCountry().size(); i++) {

                                     databaseHandler.addCountryInfo(response.body().getCountry().get(i));
                                 }
                                 for (int j = 0; j < response.body().getState().size(); j++) {

                                     databaseHandler.addStateInfo(response.body().getState().get(j));
                                 }
                                 for (int k = 0; k < response.body().getCity().size(); k++) {

                                     databaseHandler.addCityInfo(response.body().getCity().get(k));
                                 }
                                 for (int l = 0; l < response.body().getCategory().size(); l++) {

                                     databaseHandler.addCategoryInfo(response.body().getCategory().get(l));
                                 }

                             } else {

                                 progressBar.setVisibility(GONE);
                                 ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);


                                 singletonUtil.showErrorMessage(error,
                                         parentLayout, getApplicationContext());
                             }

                         }


                         @Override
                         public void onFailure(Call<MasterDataBean> call, Throwable t) {
                             // Log error here since request failed
                             Log.d(TAG, "onFailure: " + t.getMessage());


                         }
                     }

        );


    }


    private void generateKeyHash() {

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.example.dell.socialsift", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
                System.out.println("something " + something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }


    }

    private void attemptLogin() {

        // Reset errors.
        editTextEmail.setError(null);
        editTextPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (validate()) {
            showHideKeyboard();


            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            if (singletonUtil.isConnectedToInternet(LoginActivity.this)) {

                callToLoginAPI(email, password);
            } else
                singletonUtil.showSnackBar(getString(R.string.check_net_connection), parentLayout);

        } else
            singletonUtil.showSnackBar("Please provide valid details", parentLayout);

    }


    private boolean validate() {
        boolean valid = true;
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false;
            showErrorInUi(editTextEmail, Html.fromHtml("<font color='red'>Enter valid email</font>"));

        } else
            editTextEmail.setError(null);

        if (password.isEmpty() || password.length() < 4 || password.length() > 30) {
            valid = false;
            showErrorInUi(editTextPassword, Html.fromHtml("<font color='red'>Enter valid password</font>"));

        } else editTextPassword.setError(null);

        return valid;
    }

    private void showErrorInUi(EditText editText, Spanned spanned) {
        editText.setError(spanned);
        editText.requestFocus();
    }

    public void showHideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void callToLoginAPI(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClient.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<LoginBean> call = apiService.getLoginInfo(email, password, "password");

        call.enqueue(new Callback<LoginBean>() {
                         @Override
                         public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                             Log.d(TAG, "response=" + response.code());
                             progressBar.setVisibility(GONE);
                             if (response.isSuccessful()) {
                                 String token = response.body().getAccessToken();
                                 String username = response.body().getTokenType();
                                 Log.d(TAG, "token =" + token + "username =" + username);

                                 sessionManager.setLogin(true);

                                 databaseHandler.addTokenInfo(response.body());

                                 getUserData();


//                                 onLoginSuccess(token, response.body());

                             } else {

                                 ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);


                                 singletonUtil.showErrorMessage(error,
                                         parentLayout, getApplicationContext());
                             }

                         }


                         @Override
                         public void onFailure(Call<LoginBean> call, Throwable t) {
                             // Log error here since request failed
                             Log.d(TAG, "onFailure: " + t.getMessage());
                             progressBar.setVisibility(GONE);
                             singletonUtil.showSnackBar(
                                     "Unable to connect to server at this moment!! Please try again later!!",
                                     parentLayout);

//                             {"access_token":"e86210f8-08ce-47d5-840a-408e47de56fc","token_type":"bearer","refresh_token":"12c8734b-3b20-439f-b177-49324d89e846","expires_in":1789,"scope":"read write"}

                         }
                     }

        );

    }


    public void getUserData() {
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<GetUserDashboardBean> call = apiService.getUserDetails();

        call.enqueue(new Callback<GetUserDashboardBean>() {
                         @Override
                         public void onResponse(Call<GetUserDashboardBean> call, Response<GetUserDashboardBean> response) {
                             Log.d(TAG, "response=" + response.code());
//                             progressBar.setVisibility(View.GONE);
                             if (response.isSuccessful()) {


                                 sessionManager.putPref(Constants.USER_ID, response.body().getUserMasterDtl().getUserMasterDtlsId().toString(), getApplicationContext());
                                 sessionManager.putPref(Constants.FIRST_NAME, response.body().getUserMasterDtl().getFirstName(), getApplicationContext());
                                 sessionManager.putPref(Constants.LAST_NAME, response.body().getUserMasterDtl().getLastName(), getApplicationContext());
                                 sessionManager.putPref(Constants.OCCUPATION, response.body().getUserMasterDtl().getOccupation(), getApplicationContext());
                                 sessionManager.putPref(Constants.PROFILE_PIC, response.body().getUserMasterDtl().getUserProfilePhoto().toString(), getApplicationContext());

                                 Intent intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                 startActivity(intent);
                                 finish();

                             } else {
                                 ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);


                                 singletonUtil.showErrorMessage(error,
                                         parentLayout, getApplicationContext());
                             }

                         }


                         @Override
                         public void onFailure(Call<GetUserDashboardBean> call, Throwable t) {
                             // Log error here since request failed
                             Log.d(TAG, "onFailure: " + t.getMessage());

                             singletonUtil.showSnackBar(
                                     "Unable to connect to server at this moment!! Please try again later!!",
                                     parentLayout);
                         }
                     }

        );


    }


}
