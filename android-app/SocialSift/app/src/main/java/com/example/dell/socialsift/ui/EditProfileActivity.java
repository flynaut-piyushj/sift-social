package com.example.dell.socialsift.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Spanned;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.socialsift.R;
import com.example.dell.socialsift.beans.common.ErrorMessageResponse;
import com.example.dell.socialsift.beans.common.ResponseMessage;
import com.example.dell.socialsift.beans.common.ResponseMessageWrapper;
import com.example.dell.socialsift.beans.masterBean.City;
import com.example.dell.socialsift.beans.masterBean.Country;
import com.example.dell.socialsift.beans.masterBean.State;
import com.example.dell.socialsift.beans.profileBean.GetUserDetailsBean;
import com.example.dell.socialsift.beans.profileBean.UpdateProfileBean;
import com.example.dell.socialsift.beans.profileBean.UserMasterDtl;
import com.example.dell.socialsift.ui.loginUI.LoginActivity;
import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.DatabaseHandler;
import com.example.dell.socialsift.utils.commonUtils.SessionManager;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;
import com.example.dell.socialsift.utils.networking.ApiClientWithToken;
import com.example.dell.socialsift.utils.networking.ApiInterface;
import com.example.dell.socialsift.utils.networking.ErrorUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by DELL on 8/23/2017.
 */

public class EditProfileActivity extends ParentActivity implements DatePickerDialog.OnDateSetListener {
    private Toolbar toolbar;
    private EditText editTextFname, editTextLname, editTextEmail, editTextOccupation, editTextDateOfBirth;
    String cityValue, profileImage = "";
    private ProgressBar progressBar;
    private Spinner spinner_country, spinner_state, spinner_city;
    private DatabaseHandler databaseHandler;
    private List<Country> countryList = new ArrayList<Country>();
    private List<State> stateList = new ArrayList<State>();
    private List<City> cityList = new ArrayList<City>();
    private int checkCountry = 0;
    private int checkState = 0;
    private int checkCity = 0;


    private Boolean buttonStatus = false;
    private SingletonUtil singletonUtil;
    private RadioGroup radioGroupGender;
    private RelativeLayout layout;
    private RadioButton radio_button_male;
    private RadioButton radio_button_female;
    private String TAG = "EditProfileActivity";
    private Button buttonedit;
    private TextView txt_change_password;
    Boolean isEditable = false;
    private ImageView edit_image;
    private static final String IMAGE_DIRECTORY = "/sift";
    private int GALLERY = 1, CAMERA = 2;
    Uri uri;
    private SessionManager sessionManager;
    private String serverEmail;
    File file;
    private ImageView imgProfile;
    private LinearLayout layout_city,layout_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
        showHideEditTexts(isEditable);
        getUserProfile();
    }


    public void init() {
        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        editTextFname = (EditText) findViewById(R.id.editTextFname);
        editTextLname = (EditText) findViewById(R.id.editTextLname);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextOccupation = (EditText) findViewById(R.id.editTextOccupation);
        buttonedit = (Button) findViewById(R.id.buttonedit);
        buttonedit.setOnClickListener(this);
        layout_city = (LinearLayout)findViewById(R.id.layout_city);
        layout_state = (LinearLayout)findViewById(R.id.layout_state);
        edit_image = (ImageView) findViewById(R.id.edit_image);
        edit_image.setOnClickListener(this);
        editTextDateOfBirth = (EditText) findViewById(R.id.editTextDateOfBirth);
        editTextDateOfBirth.setOnClickListener(this);
        sessionManager = new SessionManager(getApplicationContext());
        spinner_country = (Spinner) findViewById(R.id.spinner_country);
        spinner_state = (Spinner) findViewById(R.id.spinner_state);
        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        databaseHandler = new DatabaseHandler(getApplicationContext());
        singletonUtil = SingletonUtil.getSingletonConfigInstance();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        txt_change_password = (TextView) findViewById(R.id.txt_change_password);
        txt_change_password.setOnClickListener(this);
        radioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);


        radio_button_male = (RadioButton) findViewById(R.id.radio_button_male);

        radio_button_female = (RadioButton) findViewById(R.id.radio_button_female);
        layout = (RelativeLayout) findViewById(R.id.parentLayout);
    }

    public void getUserProfile() {
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<GetUserDetailsBean> call = apiService.getProfileData();

        call.enqueue(new Callback<GetUserDetailsBean>() {
                         @Override
                         public void onResponse(Call<GetUserDetailsBean> call, Response<GetUserDetailsBean> response) {
                             Log.d(TAG, "response=" + response.code());
//                             progressBar.setVisibility(View.GONE);
                             if (response.isSuccessful()) {

                                 setProfileData(response.body().getUserMasterDtl());

                             } else {
                                 ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);
                                 singletonUtil.showErrorMessage(error,
                                         layout, getApplicationContext());
                             }

                         }


                         @Override
                         public void onFailure(Call<GetUserDetailsBean> call, Throwable t) {
                             // Log error here since request failed
                             Log.d(TAG, "onFailure: " + t.getMessage());
                             singletonUtil.showSnackBar(
                                     "Unable to connect to server at this moment!! Please try again later!!",
                                     layout);

                         }
                     }

        );


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonedit:
                if (isEditable == false) {
                    isEditable = true;
                    showHideEditTexts(true);
                    buttonedit.setText("Save");
                    edit_image.setVisibility(View.VISIBLE);
                    edit_image.setOnClickListener(this);

                } else {

                    isEditable = false;
                    showHideEditTexts(false);
                    layout.setVisibility(View.GONE);
                    buttonedit.setText("Edit");
                    edit_image.setVisibility(View.GONE);


                    if (singletonUtil.isConnectedToInternet(EditProfileActivity.this)) {

                        //set profile data
                        updateProfile();
                    } else
                        singletonUtil.showSnackBar(getString(R.string.check_net_connection), layout);

                }

//getUserProfile();
                break;

            case R.id.edit_image:
                selectWindow(Constants.PROFILE_PIC_REQ);

                break;


            case R.id.txt_change_password:
                changePassword();
                break;

            case R.id.editTextDateOfBirth:

                editTextDateOfBirth.setText("");

                Calendar now = Calendar.getInstance();
                Calendar calendar = Calendar.getInstance();

                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        EditProfileActivity.this,
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


    public void updateProfile() {

        if (validate()) {
            String fName = editTextFname.getText().toString();
            String lName = editTextLname.getText().toString();
            String email;
            if ((editTextEmail.getText().toString()).equals(serverEmail)) {
                email = "";
            } else {
                email = editTextEmail.getText().toString();
            }
            String occupation = editTextOccupation.getText().toString();
            String dateOfBirth = editTextDateOfBirth.getText().toString();
            showHideKeyboard();
            int selectedIdPlan = radioGroupGender.getCheckedRadioButtonId();
            RadioButton radioButtonGender = (RadioButton) findViewById(selectedIdPlan);
            if (radioButtonGender == null) {


                singletonUtil.showSnackBar("Please provide valid gender", layout);
            } else {
//                showHideKeyboard();
                String gender;
                if (radioButtonGender.getText().toString().equalsIgnoreCase("Male"))
                    gender = "M";
                else
                    gender = "F";
                if (cityValue == null || cityValue.equals("")) {
                    UpdateProfileBean bean = new UpdateProfileBean(fName, lName, profileImage, email,
                            gender, occupation, "2", dateOfBirth);
                    callForUpdateProfile(bean);
                } else {
                    if (singletonUtil.isConnectedToInternet(EditProfileActivity.this)) {

                        UpdateProfileBean bean = new UpdateProfileBean(fName, lName, profileImage, email,
                                gender, occupation, "2", dateOfBirth);
                        callForUpdateProfile(bean);

                    } else
                        singletonUtil.showSnackBar(getString(R.string.check_net_connection), layout);
                }


            }
        }


    }


    private void showErrorInUi(EditText editText, Spanned spanned) {
        editText.setError(spanned);
        editText.requestFocus();
    }

    public void showHideKeyboard() {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


    public void changePassword() {

        final AlertDialog.Builder mAlertDialogBuilder = new AlertDialog.Builder(EditProfileActivity.this);
        LayoutInflater inflater = EditProfileActivity.this.getLayoutInflater();
        final View mDialogView = inflater.inflate(R.layout.layout_change_password, null);
        mAlertDialogBuilder.setView(mDialogView);
        final EditText editText_old_password = (EditText) mDialogView.findViewById(R.id.editText_old_password);
        final EditText editText_password = (EditText) mDialogView.findViewById(R.id.editText_password);
        final EditText editText_confirm_password = (EditText) mDialogView.findViewById(R.id.editText_confirm_password);
        ImageView img_cancel = (ImageView) mDialogView.findViewById(R.id.img_cancel);
        final LinearLayout relativeLayoutParentDialog = (LinearLayout) mDialogView.findViewById(R.id.relativeLayoutParentDialog);
        TextView btnOk = (TextView) mDialogView.findViewById(R.id.txt_verify);

        final AlertDialog alertDialog = mAlertDialogBuilder.create();
        alertDialog.show();

        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String oldPassword = editText_old_password.getText().toString();
                final String newPassword = editText_password.getText().toString();
                final String confirmPassword = editText_confirm_password.getText().toString();

                if (oldPassword.equals("") || oldPassword.length() < 4) {
                    singletonUtil.showSnackBar("Please enter old Password", relativeLayoutParentDialog);
                } else if (newPassword == null || newPassword.length() < 4) {
                    singletonUtil.showSnackBar("Please enter valid password", relativeLayoutParentDialog);
                } else if (!newPassword.equals(confirmPassword)) {
                    singletonUtil.showSnackBar("Please confirm the password", relativeLayoutParentDialog);
                } else {


                    if (singletonUtil.isConnectedToInternet(EditProfileActivity.this)) {
                        callTochangePassword(oldPassword, newPassword);
                    } else
                        singletonUtil.showSnackBar(getString(R.string.check_net_connection), layout);


                }

            }

        });

    }

    public void callTochangePassword(String oldPass, String newPass) {

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("oldPassword", oldPass);
        parameters.put("newPassword", newPass);

        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<ResponseMessageWrapper> call = apiService.changePassword(parameters);

        call.enqueue(new Callback<ResponseMessageWrapper>() {
            @Override
            public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
                Log.d(TAG, "response=" + response.code());
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                    singletonUtil.showSnackBar(
                            "Password changed sucessfully",
                            (RelativeLayout) findViewById(R.id.relativeLayoutParent));

                    sessionManager.setLogin(false);
                    databaseHandler.deleteTokenInfo();
                    sessionManager.removeKey(Constants.FIRST_NAME);
                    sessionManager.removeKey(Constants.OCCUPATION);
                    sessionManager.removeKey(Constants.LAST_NAME);
                    sessionManager.removeKey(Constants.PROFILE_PIC);

                    Intent intent_logout = new Intent(EditProfileActivity.this, LoginActivity.class);
                    intent_logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent_logout);

                } else {
                    progressBar.setVisibility(View.GONE);
                    ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);
                    // … and use it to show error information

                    singletonUtil.showErrorMessage(error, (RelativeLayout) findViewById(R.id.relativeLayoutParent), EditProfileActivity.this);
                    // … or just log the issue like we’re doing :)

                }

            }

            @Override
            public void onFailure(Call<ResponseMessageWrapper> call, Throwable t) {
                // Log error here since request failed
                Log.d(TAG, "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
                singletonUtil.showSnackBar(
                        "Unable to connect to server at this moment!! Please try again later!!",
                        (RelativeLayout) findViewById(R.id.relativeLayoutParent));

            }
        });
    }


    public void selectWindow(final int requestCode) {

        final Dialog mDialog;
        mDialog = new Dialog(EditProfileActivity.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_select_media_for_image);
        LinearLayout camera, gallery;
        camera = (LinearLayout) mDialog.findViewById(R.id.layout1);
        gallery = (LinearLayout) mDialog.findViewById(R.id.layout2);
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                chooseFromCamera(requestCode);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                chooseFromGallery(requestCode);
            }
        });
        mDialog.show();
    }

    public void chooseFromGallery(int requestCode) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);

    }

    public void chooseFromCamera(int requestCode) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    profileImage = singletonUtil.getEncodedString(bitmap);
                    Toast.makeText(EditProfileActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imgProfile.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(EditProfileActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imgProfile.setImageBitmap(thumbnail);
            profileImage = singletonUtil.getEncodedString(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(EditProfileActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


    private void callForUpdateProfile(UpdateProfileBean bean) {

        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<ResponseMessage> call = apiService.updateProfileData(bean);

        call.enqueue(new Callback<ResponseMessage>() {
                         @Override
                         public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                             Log.d(TAG, "response=" + response.code());
//                             progressBar.setVisibility(View.GONE);
                             if (response.isSuccessful()) {


                             } else {

                             }

                         }


                         @Override
                         public void onFailure(Call<ResponseMessage> call, Throwable t) {
                             // Log error here since request failed
                             Log.d(TAG, "onFailure: " + t.getMessage());


                         }
                     }

        );


    }

    private boolean validate() {
        boolean valid = true;

        String fName = editTextFname.getText().toString();
        String lName = editTextLname.getText().toString();
        String Email = editTextEmail.getText().toString();
        String occupation = editTextOccupation.getText().toString();
        String DOB = editTextDateOfBirth.getText().toString();
        if (fName.isEmpty() || fName.length() < 4) {
            valid = false;

            singletonUtil.showSnackBar("Please enter first name", layout);
        }


        if (lName.isEmpty() || lName.length() < 4) {
            valid = false;
            singletonUtil.showSnackBar("Please enter last name", layout);

        }

        if (Email.isEmpty() || Email.length() < 4 || !Patterns.EMAIL_ADDRESS.matcher(Email.toString().trim()).matches()) {
            valid = false;
            singletonUtil.showSnackBar("Please enter email", layout);

        }

        if (occupation.isEmpty() || occupation.length() < 4) {
            valid = false;

            singletonUtil.showSnackBar("Please enter occupation", layout);
        }


        if (DOB.isEmpty() || DOB.length() < 4) {
            valid = false;
            singletonUtil.showSnackBar("Please enter date of birth", layout);

        }


        return valid;
    }

    public void setProfileData(UserMasterDtl bean) {
        sessionManager.putPref(Constants.USER_ID,bean.getUserMasterDtlsId().toString(),getApplicationContext());
        sessionManager.putPref(Constants.FIRST_NAME,bean.getFirstName(),getApplicationContext());
        sessionManager.putPref(Constants.LAST_NAME,bean.getLastName(),getApplicationContext());
        sessionManager.putPref(Constants.OCCUPATION,bean.getOccupation(),getApplicationContext());
        editTextFname.setText(bean.getFirstName());
        editTextLname.setText(bean.getLastName());
        editTextEmail.setText(bean.getEmailId());
        editTextOccupation.setText(bean.getOccupation());
        editTextDateOfBirth.setText(bean.getDateOfBirth());
        serverEmail = bean.getEmailId();
        if (bean.getGender().equals("F")) {
            radio_button_female.setChecked(true);
        } else {
            radio_button_male.setChecked(true);
        }

        setSpinner(bean);
//
        if (bean.getUserProfilePhoto() != null || !bean.getUserProfilePhoto().equals(""))
            Glide.with(getApplicationContext()).load(bean.getUserProfilePhoto()).placeholder(R.drawable.default_image).into(imgProfile);


//        for (int i = 0; i < radioGroupGender.getChildCount(); i++) {
//            radioGroupGender.getChildAt(i).setEnabled(false);
//        }
    }

    private void setSpinner(final UserMasterDtl bean) {

        checkCountry = 0;
        checkState = 0;
        checkCity = 0;

        List<String> countryNameList = new ArrayList<String>();
        final List<String> stateNameList = new ArrayList<String>();
        final List<String> cityNameList = new ArrayList<String>();
        countryNameList.add("Select Country");
        for (int i = 0; i < databaseHandler.getCountryList().size(); i++) {

            countryNameList.add(databaseHandler.getCountryList().get(i).getName());

        }

        final ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(EditProfileActivity.this,

                R.layout.simple_spinner_dropdown_item, countryNameList);

        spinner_country.setAdapter(countryAdapter);

        spinner_country.setSelection(countryAdapter.getPosition(databaseHandler.getCountryName(bean.getCountry())));
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                System.out.println("IN COUNTRY SPINNER");
                checkCountry++;
                layout_city.setVisibility(View.GONE);
                layout_state.setVisibility(View.GONE);
                String countryID;

                if (checkCountry > 0) {
                    System.out.println("IN COUNTRY SPINNER  11111");
                    if (parent.getSelectedItem().toString().equals("Select Country")) {
                        singletonUtil.showSnackBar("Please select country", layout);


                    } else {
                        System.out.println("parent.getItemAtPosition(pos).toString()");
                        countryID = databaseHandler.getCountryID(parent.getItemAtPosition(pos).toString());

layout_state.setVisibility(View.VISIBLE);
                        if (countryID != null) {


                            stateNameList.add("Select State");

                            for (int i = 0; i < databaseHandler.getStateByCountry(countryID).size(); i++) {


                                stateNameList.add(databaseHandler.getStateByCountry(countryID).get(i).getName());

                            }
                        }
                        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(EditProfileActivity.this,

                                R.layout.simple_spinner_dropdown_item, stateNameList);

                        spinner_state.setAdapter(stateAdapter);


                    }
                }


            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        stateNameList.add("Select State");

        for (int i = 0; i < databaseHandler.getStateByCountry(bean.getCountry()).size(); i++) {


            stateNameList.add(databaseHandler.getStateByCountry(bean.getCountry()).get(i).getName());

        }

        final ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(EditProfileActivity.this,

                R.layout.simple_spinner_dropdown_item, stateNameList);

        spinner_state.setAdapter(stateAdapter);
        spinner_state.setSelection(stateAdapter.getPosition(databaseHandler.getStateName(bean.getState())));

        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                checkState++;
                layout_city.setVisibility(View.GONE);
                String StateID;
                if (checkState > 1) {

                    if (parent.getSelectedItem().toString().equals("Select State")) {
                        singletonUtil.showSnackBar("Please select state", layout);


                    } else {

                        layout_city.setVisibility(View.VISIBLE);
                        StateID = databaseHandler.getStateID(parent.getItemAtPosition(pos).toString());

                        if (StateID != null) {

                            cityNameList.add("Select city");

                            for (int i = 0; i < databaseHandler.getCityByState(StateID).size(); i++) {

                                cityNameList.add(databaseHandler.getCityByState(StateID).get(i).getName());

                            }

                        }
                        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(EditProfileActivity.this,

                                R.layout.simple_spinner_dropdown_item, cityNameList);

                        spinner_city.setAdapter(cityAdapter);

                    }
                }


            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        cityNameList.add("Select city");

        for (int i = 0; i < databaseHandler.getCityByState(bean.getState()).size(); i++) {

            cityNameList.add(databaseHandler.getCityByState(bean.getState()).get(i).getName());

        }

        final ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(EditProfileActivity.this,

                R.layout.simple_spinner_dropdown_item, cityNameList);

        spinner_city.setAdapter(cityAdapter);
        spinner_city.setSelection(cityAdapter.getPosition(databaseHandler.getCityName(bean.getCity())));

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                checkCity++;
                spinner_city.setSelection(cityAdapter.getPosition(databaseHandler.getCityName(bean.getCity())));
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

    private void showHideEditTexts(boolean value) {
        editTextFname.setFocusableInTouchMode(value);
        editTextFname.setFocusable(value);
        editTextFname.setCursorVisible(value);

        editTextLname.setFocusableInTouchMode(value);
        editTextLname.setFocusable(value);
        editTextLname.setCursorVisible(value);

        editTextEmail.setFocusableInTouchMode(value);
        editTextEmail.setFocusable(value);
        editTextEmail.setCursorVisible(value);

        editTextOccupation.setFocusableInTouchMode(value);
        editTextOccupation.setFocusable(value);
        editTextOccupation.setCursorVisible(value);

        spinner_country.setEnabled(value);
        spinner_state.setEnabled(value);
        spinner_city.setEnabled(value);

        editTextDateOfBirth.setFocusableInTouchMode(value);
        editTextDateOfBirth.setFocusable(value);
        editTextDateOfBirth.setCursorVisible(value);
        editTextDateOfBirth.setClickable(value);

        radioGroupGender.setClickable(value);

//radioGroupGender.setEnabled(value);
//        for (int i = 0; i < radioGroupGender.getChildCount(); i++) {
//            radioGroupGender.getChildAt(i).setEnabled(value);
//        }

    }

    public void setToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mToolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mToolbarTitle.setText("Edit");
        toolbar.setNavigationIcon(R.drawable.ic_back2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String dayofmonth = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
        String date = "" + year + "-" + (++monthOfYear) + "-" + dayofmonth;
        editTextDateOfBirth.setText(date);
        Log.d("TimePicker", "" + date);
    }
}
