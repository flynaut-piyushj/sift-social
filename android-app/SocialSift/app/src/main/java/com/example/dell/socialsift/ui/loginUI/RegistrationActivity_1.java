package com.example.dell.socialsift.ui.loginUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.ui.ParentActivity;
import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import static com.example.dell.socialsift.R.id.editTextFname;

/**
 * Created by ${Akshata} on ${10/17/2016}.
 */
public class RegistrationActivity_1 extends ParentActivity implements DatePickerDialog.OnDateSetListener {
     private EditText edtTextFname;
    private EditText edtTextLname;
    private EditText edtTextEmail;
    private EditText edtTextPassword;
    private EditText edtTextCpassword;
    private Button btnsignin;
    private SingletonUtil singletonUtil;
    private String TAG = "RegistrationActivity_1";
    private RelativeLayout parentLayout;
    private RadioGroup radioGroupGender;
    private RelativeLayout layout;
    private RadioButton radio_button_male;
    private RadioButton radio_button_female;
    private EditText editTextDOB;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_registration_1);

        init();
    }

    private void init(){
        btnsignin = (Button) findViewById(R.id.buttonsignin);
        btnsignin.setOnClickListener(this);
        layout = (RelativeLayout) findViewById(R.id.layout_next);
        layout.setOnClickListener(this);
        edtTextFname = (EditText)findViewById(editTextFname);
        edtTextLname = (EditText)findViewById(R.id.editTextLname);
       edtTextEmail = (EditText)findViewById(R.id.editTextEmail);
        edtTextPassword = (EditText)findViewById(R.id.editTextPassword);
         edtTextCpassword = (EditText)findViewById(R.id.editTextCpassword);
        singletonUtil = SingletonUtil.getSingletonConfigInstance();
        radioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);

        radio_button_male = (RadioButton) findViewById(R.id.radio_button_male);
//        radio_button_male.setChecked(true);
        editTextDOB = (EditText) findViewById(R.id.editTextBirthDate);
        editTextDOB.setOnClickListener(this);
        radio_button_female = (RadioButton) findViewById(R.id.radio_button_female);
        parentLayout = (RelativeLayout)findViewById(R.id.parentLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonsignin:
                attemptRegistration();
//                Intent register_intent = new Intent(getApplicationContext(),RegistrationActivity_2.class);
//                startActivity(register_intent);

                break;
            case R.id.layout_next:
//                attemptRegister();

                break;

            case R.id.editTextBirthDate:

                editTextDOB.setText("");

                Calendar now = Calendar.getInstance();
                Calendar calendar = Calendar.getInstance();

                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        RegistrationActivity_1.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
//                calendar.add(Calendar.DAY_OF_MONTH, 1);
                dpd.setMaxDate(calendar);

                dpd.show(this.getFragmentManager(), "Datepickerdialog");
                break;
        }
    }


    private void attemptRegistration() {

        edtTextFname.setError(null);
         edtTextLname.setError(null);
        edtTextEmail.setError(null);
        edtTextPassword.setError(null);
        edtTextCpassword.setError(null);
        editTextDOB.setError(null);

        // Store values at the time of the login attempt.
        String fName = edtTextFname.getText().toString();
        String lName = edtTextLname.getText().toString();
        String Email = edtTextEmail.getText().toString();
        String Password = edtTextPassword.getText().toString();
        String cPassword = edtTextCpassword.getText().toString();
String DOB = editTextDOB.getText().toString();

        if (validate()) {
            showHideKeyboard();
            int selectedIdPlan = radioGroupGender.getCheckedRadioButtonId();
            RadioButton radioButtonGender = (RadioButton) findViewById(selectedIdPlan);
            if(radioButtonGender == null ){


                singletonUtil.showSnackBar("Please provide valid gender", parentLayout);
            }
            else {
                String gender;
                if (radioButtonGender.getText().toString().equalsIgnoreCase("Male"))
                    gender = "M";
                else
                    gender = "F";
                showHideKeyboard();
                singletonUtil.showSnackBar("gender   "+gender, parentLayout);
                if (singletonUtil.isConnectedToInternet(RegistrationActivity_1.this)) {

                    Intent intent = new Intent(getApplicationContext(),RegistrationActivity_2.class);
                    intent.putExtra(Constants.FIRST_NAME,fName);
                    intent.putExtra(Constants.LAST_NAME,lName);
                    intent.putExtra(Constants.EMAIL,Email);
                    intent.putExtra(Constants.GENDER,gender);
                    intent.putExtra(Constants.DATE__OF_BIRTH,DOB);
                    intent.putExtra(Constants.PASSWORD,Password);

                    startActivity(intent);

                } else
                    singletonUtil.showSnackBar(getString(R.string.check_net_connection), parentLayout);
            }
        } else
            singletonUtil.showSnackBar("Please provide valid details", parentLayout);

    }



    private boolean validate() {
        boolean valid = true;

        String fName = edtTextFname.getText().toString();
        String lName = edtTextLname.getText().toString();
        String Email = edtTextEmail.getText().toString();
        String Password = edtTextPassword.getText().toString();
        String cPassword = edtTextCpassword.getText().toString();
        String DOB = editTextDOB.getText().toString();
        if (fName.isEmpty() || fName.length() < 4) {
            valid = false;
            showErrorInUi(edtTextFname, Html.fromHtml("<font color='red'>Enter first name</font>"));

        } else
            edtTextFname.setError(null);

        if (lName.isEmpty() || lName.length() < 4) {
            valid = false;
            showErrorInUi(edtTextLname, Html.fromHtml("<font color='red'>Enter last name</font>"));

        } else
            edtTextLname.setError(null);

        if (Email.isEmpty() || Email.length() < 4 || !Patterns.EMAIL_ADDRESS.matcher(Email.toString().trim()).matches()) {
            valid = false;
            showErrorInUi(edtTextEmail, Html.fromHtml("<font color='red'>Enter valid email</font>"));

        } else
            edtTextEmail.setError(null);

        if (Password.isEmpty() || Password.length() < 4) {
            valid = false;
            showErrorInUi(edtTextPassword, Html.fromHtml("<font color='red'>Enter valid password</font>"));

        } else
            edtTextPassword.setError(null);

        if (cPassword.isEmpty() || cPassword.length() < 4 || !cPassword.equals(Password)) {
            valid = false;
            showErrorInUi(edtTextCpassword, Html.fromHtml("<font color='red'>Confirm password</font>"));

        } else
            edtTextCpassword.setError(null);

        if (DOB.isEmpty() || DOB.length() < 4 ) {
            valid = false;
            showErrorInUi(editTextDOB, Html.fromHtml("<font color='red'>Please enter date of birth</font>"));

        } else
            editTextDOB.setError(null);


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




    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String dayofmonth = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
        String date = "" + year + "-" + (++monthOfYear) + "-" + dayofmonth;
        editTextDOB.setText(date);
        Log.d("TimePicker", "" + date);
    }
}
