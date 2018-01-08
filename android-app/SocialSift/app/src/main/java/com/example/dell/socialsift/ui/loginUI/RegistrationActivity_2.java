package com.example.dell.socialsift.ui.loginUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.beans.bodyBean.RegistrationBean;
import com.example.dell.socialsift.beans.common.ErrorMessageResponse;
import com.example.dell.socialsift.beans.common.ResponseMessage;
import com.example.dell.socialsift.beans.masterBean.City;
import com.example.dell.socialsift.beans.masterBean.Country;
import com.example.dell.socialsift.beans.masterBean.State;
import com.example.dell.socialsift.ui.ParentActivity;
import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.DatabaseHandler;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;
import com.example.dell.socialsift.utils.networking.ApiClientWithoutToken;
import com.example.dell.socialsift.utils.networking.ApiInterface;
import com.example.dell.socialsift.utils.networking.ErrorUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.view.View.GONE;

/**
 * Created by ${Akshata} on ${10/17/2016}.
 */
public class RegistrationActivity_2 extends ParentActivity {


    private Button btnsignin;
    private EditText editTextOccupation;
    private RelativeLayout layout;
    private Spinner spinner_country, spinner_state, spinner_city;
    private DatabaseHandler databaseHandler;
    private List<Country> countryList = new ArrayList<Country>();
    private List<State> stateList = new ArrayList<State>();
    private List<City> cityList = new ArrayList<City>();
    private int checkCountry = 0;
    private int checkState = 0;
    private int checkCity = 0;
    private SingletonUtil singletonUtil;
    private String countryValue;
    private String stateValue;
    private String cityValue;
    private String fName, lName, email, dateOfBirth, gender, pasword;
    private ProgressBar progressBar;
    private RelativeLayout layout_city,layout_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_registration_2);
        init();
    }

    private void init() {
        btnsignin = (Button) findViewById(R.id.buttonsignin);
        btnsignin.setOnClickListener(this);
        layout = (RelativeLayout) findViewById(R.id.parentLayout);
//        layout.setOnClickListener(this);
        editTextOccupation = (EditText) findViewById(R.id.editTextOccupation);
        spinner_country = (Spinner) findViewById(R.id.spinner_country);
        spinner_state = (Spinner) findViewById(R.id.spinner_state);
        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        databaseHandler = new DatabaseHandler(getApplicationContext());
        singletonUtil = SingletonUtil.getSingletonConfigInstance();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        layout_city = (RelativeLayout)findViewById(R.id.layout_city);
        layout_state = (RelativeLayout)findViewById(R.id.layout_state);
        setSpinner();
        getIntentData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonsignin:
                attemptRegister();
//                Intent register_intent = new Intent(getApplicationContext(),LoginActivity.class);
//                startActivity(register_intent);
                break;


        }
    }


    public void getIntentData() {

        fName = getIntent().getStringExtra(Constants.FIRST_NAME);
        lName = getIntent().getStringExtra(Constants.LAST_NAME);
        email = getIntent().getStringExtra(Constants.EMAIL);
        dateOfBirth = getIntent().getStringExtra(Constants.DATE__OF_BIRTH);
        gender = getIntent().getStringExtra(Constants.GENDER);
        pasword = getIntent().getStringExtra(Constants.PASSWORD);


    }


    private void setSpinner() {

        checkCountry = 0;
        checkState = 0;
        checkCity = 0;

        List<String> countryNameList = new ArrayList<String>();

        countryNameList.add("Select Country");
        for (int i = 0; i < databaseHandler.getCountryList().size(); i++) {

            countryNameList.add(databaseHandler.getCountryList().get(i).getName());

        }

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(RegistrationActivity_2.this,

                R.layout.simple_spinner_dropdown_item, countryNameList);

        spinner_country.setAdapter(countryAdapter);


        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                checkCountry++;
                layout_city.setVisibility(View.GONE);
                layout_state.setVisibility(View.GONE);
                if (checkCountry > 1) {
                    cityValue = null;
                    if (parent.getSelectedItem().toString().equals("Select Country")) {
                        singletonUtil.showSnackBar("Please select country", layout);

                        final List<String> stateNameList = new ArrayList<String>();
                    } else {
                        layout_state.setVisibility(View.VISIBLE);
                        System.out.println("parent.getItemAtPosition(pos).toString()");
                        String countryID = databaseHandler.getCountryID(parent.getItemAtPosition(pos).toString());

                        final List<String> stateNameList = new ArrayList<String>();

                        checkState = 0;
                        checkCity = 0;
                        if (countryID != null) {


                            stateNameList.add("Select State");

                            for (int i = 0; i < databaseHandler.getStateByCountry(countryID).size(); i++) {


                                stateNameList.add(databaseHandler.getStateByCountry(countryID).get(i).getName());

                            }
                        }
                        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(RegistrationActivity_2.this,

                                R.layout.simple_spinner_dropdown_item, stateNameList);

                        spinner_state.setAdapter(stateAdapter);

                    }
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                checkCountry++;
                cityValue = null;
                layout_city.setVisibility(View.GONE);
                if (checkCountry > 1) {

                    if (parent.getSelectedItem().toString().equals("Select State")) {
                        singletonUtil.showSnackBar("Please select state", layout);


                    } else {
                        layout_city.setVisibility(View.VISIBLE);
                        checkCity = 0;
                        checkState = 0;
                        final List<String> cityNameList = new ArrayList<String>();


                        String StateID = databaseHandler.getStateID(parent.getItemAtPosition(pos).toString());

                        if (StateID != null) {

                            cityNameList.add("Select city");

                            for (int i = 0; i < databaseHandler.getCityByState(StateID).size(); i++) {

                                cityNameList.add(databaseHandler.getCityByState(StateID).get(i).getName());

                            }

                        }
                        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(RegistrationActivity_2.this,

                                R.layout.simple_spinner_dropdown_item, cityNameList);

                        spinner_city.setAdapter(cityAdapter);

                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                checkCity++;

                if (checkCity > 1) {

                    if (parent.getSelectedItem().toString().equals("Select city")) {
                        singletonUtil.showSnackBar("Please select city", layout);

                    } else {
                        cityValue = databaseHandler.getCityID(parent.getItemAtPosition(pos).toString());


                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    private void attemptRegister() {
        String occupation = editTextOccupation.getText().toString();
        editTextOccupation.setError(null);

        if (validate()) {
            showHideKeyboard();

            if (cityValue == null || cityValue.equals("")) {


                singletonUtil.showSnackBar("Please select city", layout);
            } else {


                if (singletonUtil.isConnectedToInternet(RegistrationActivity_2.this)) {


                    RegistrationBean bean = new RegistrationBean
                            (fName, lName, email, dateOfBirth, gender, pasword, cityValue, occupation);

                    callForRegistration(bean);
                } else
                    singletonUtil.showSnackBar(getString(R.string.check_net_connection), layout);
            }
        } else
            singletonUtil.showSnackBar("Please provide valid details", layout);


    }


    private boolean validate() {
        boolean valid = true;

        String occupation = editTextOccupation.getText().toString();
        if (occupation.isEmpty() || occupation.length() < 4) {
            valid = false;
            showErrorInUi(editTextOccupation, Html.fromHtml("<font color='red'>Enter first name</font>"));

        } else
            editTextOccupation.setError(null);

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

    public void callForRegistration(RegistrationBean bean) {

        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClientWithoutToken.getClient();
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<ResponseMessage> call = apiService.callRegistration(bean);

        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                Log.d("Vendor", "response=" + response.code());
                progressBar.setVisibility(GONE);
                if (response.isSuccessful()) {


                    singletonUtil.showSnackBar("registration completed!!!", layout);
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);


                } else {
                    progressBar.setVisibility(GONE);
                    ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);


                    singletonUtil.showErrorMessage(error,
                            layout, getApplicationContext());

                }

            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                // Log error here since request failed
                Log.d("Vendor", "onFailure: " + t.getMessage());
                progressBar.setVisibility(GONE);


                singletonUtil.showSnackBar(
                        "Unable to connect to server at this moment!! Please try again later!!",
                        layout);

            }
        });


    }


}
