//package com.example.dell.socialsift.ui;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Window;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.RelativeLayout;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toolbar;
//
//import com.example.dell.socialsift.R;
//import com.example.dell.socialsift.beans.common.ResponseMessageWrapper;
//import com.example.dell.socialsift.ui.loginUI.LoginActivity;
//import com.example.dell.socialsift.utils.commonUtils.Constants;
//import com.example.dell.socialsift.utils.commonUtils.SessionManager;
//import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;
//import com.example.dell.socialsift.utils.networking.ApiClient;
//import com.example.dell.socialsift.utils.networking.ApiClientWithToken;
//import com.example.dell.socialsift.utils.networking.ApiInterface;
//import com.example.dell.socialsift.utils.networking.ErrorUtils;
//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//import retrofit2.Call;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
///**
// * Created by DELL on 10/3/2017.
// */
//
//public class EditProfileSample extends ParentActivity implements DatePickerDialog.OnDateSetListener {
//
//
//private EditText etFname;
//private EditText etLname;
//
//private Toolbar toolbar;
//private String ProfilePhoto = "";
//private EditText etMobileNo;
//private EditText etSociety;
//private EditText etAadhar;
//private EditText etGender;
//private Spinner Spinnerprofession;
//private String SpinnerprofessionValue;
//        EditText edt_alternate_contact;
//        LinearLayout layout;
//private LinearLayout relativeLayoutParentDialog;
//        ImageView img_edit_phone, img_edit_date, img_edit_alt_phone;
//private FloatingActionButton buttonSave;
//        Boolean isEditable = false;
//private RelativeLayout relativeLayoutParent;
//private SingletonUtil singletonUtil;
//private String TAG = EditProfileSample.class.getName();
//private EditText etDOB;
//        LinearLayout view_spinner;
//        ImageView edit_image;
//private TextView txt_change_password;
//private SessionManager sessionManager;
//        RadioGroup employer_radio;
//        ProgressBar progressBar;
//        String VisitId = "";
//        String Profession = "", imageUrl = "", Age = "";
//        Integer genderCode;
//private static int PHOTO_REQ_CODE = -999;
//        Uri uri;
//        File file;
//        CropImageView imageCrop;
//        String firstName = "", lastName = "", mobileNo = "", societyNmae = "", aadharNo = "", gender = "", altNumber = "", DateOfBirth = "";
//        CircleImageView image_profile;
//public List<ProfessionTypeDetail> professionTypeList = new ArrayList<ProfessionTypeDetail>();
//
//@Override
//protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile_sample);
//        relativeLayoutParent = (RelativeLayout) findViewById(R.id.relativeLayoutParent);
//        singletonUtil = SingletonUtil.getSingletonConfigInstance();
//        init();
//        setToolbar("Profile");
//        showHideEditTexts(isEditable);
//
//
//        if (singletonUtil.isConnectedToInternet(ProfileActivity.this)) {
//
//        //set profile data
//        callProfileData();
//        } else
//        singletonUtil.showSnackBar(getString(R.string.check_net_connection), relativeLayoutParent);
//        }
//
//public void setToolbar(String s) {
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        TextView mToolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
//        mToolbarTitle.setText(s);
//        }
//
//public void init() {
//        imageCrop = (CropImageView) findViewById(R.id.cropImageView);
//        edit_image = (ImageView) findViewById(R.id.edit_image);
//        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//        view_spinner = (LinearLayout) findViewById(R.id.view_spinner);
//        etFname = (EditText) findViewById(R.id.etFname);
//        etLname = (EditText) findViewById(R.id.etLname);
////        etName = (EditText) findViewById(R.id.etName);
//        etMobileNo = (EditText) findViewById(R.id.etMobileNo);
//        etDOB = (EditText) findViewById(R.id.et_dob);
//        etSociety = (EditText) findViewById(R.id.etSociety);
//        etAadhar = (EditText) findViewById(R.id.etAadhar);
//        etGender = (EditText) findViewById(R.id.etGender);
//        Spinnerprofession = (Spinner) findViewById(R.id.spinnerProfession);
//        sessionManager = new SessionManager(getApplicationContext());
//        edt_alternate_contact = (EditText) findViewById(R.id.edt_alternate_contact);
//        layout = (LinearLayout) findViewById(R.id.layout_radio);
//        buttonSave = (FloatingActionButton) findViewById(R.id.fab_edit);
//        buttonSave.setOnClickListener(this);
//        showHideEditTexts(true);
//        txt_change_password = (TextView) findViewById(R.id.txt_change_password);
//        txt_change_password.setOnClickListener(this);
//        image_profile = (CircleImageView) findViewById(R.id.profile_image);
//        img_edit_phone = (ImageView) findViewById(R.id.img_edit_phone);
//        img_edit_date = (ImageView) findViewById(R.id.img_edit_date);
//        img_edit_alt_phone = (ImageView) findViewById(R.id.img_edit_alt_phone);
//
//        img_edit_phone.setOnClickListener(this);
//        img_edit_alt_phone.setOnClickListener(this);
//        employer_radio = (RadioGroup) findViewById(R.id.employer_radio);
//        employer_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//@Override
//public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//        RadioButton selectedRb = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
//
//        if (selectedRb != null) {
//
//        gender = selectedRb.getText().toString();
//        etGender.setText(gender);
//        if (gender.equals("Male")) {
//        genderCode = 20;
//        } else {
//        genderCode = 21;
//        }
//
//        }
//
//
//        }
//        });
//
//
//        }
//
//
//@Override
//public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        return true;
//        }
//
//@Override
//public boolean onOptionsItemSelected(final MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
//        switch (item.getItemId()) {
//
//        //call onBackPressed() on pressing home button(so that it works same as hardware back button)
//        case android.R.id.home:
//        onBackPressed();
//        break;
//        case R.id.go_to_home:
//        GotoHome();
//
//        break;
//default:
//        return super.onOptionsItemSelected(item);
//
//
//        }
//        return true;
//        }
//
//
//public void GotoHome() {
//        Intent i = new Intent(getApplicationContext(), MainDashboardActivity.class);
//        startActivity(i);
//        }
//
//@Override
//public void onBackPressed() {
//        super.onBackPressed();
//        Intent i = new Intent(getApplicationContext(), MainDashboardActivity.class);
//        startActivity(i);
//        finish();
//
//        }
//
//
////call for updating profile
//public void updateProfileDetails() {
//
//        firstName = etFname.getText().toString();
//        lastName = etLname.getText().toString();
//        mobileNo = etMobileNo.getText().toString();
//        societyNmae = etSociety.getText().toString();
//        aadharNo = etAadhar.getText().toString();
//        gender = etGender.getText().toString();
//        altNumber = edt_alternate_contact.getText().toString();
//        DateOfBirth = etDOB.getText().toString();
//        if (VisitId == "") {
//        singletonUtil.showSnackBar(
//        "Please select profession",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//        } else {
//final UpdateProfile bean = new UpdateProfile(firstName, lastName, genderCode, gender, mobileNo, DateOfBirth
//        , imageUrl, VisitId, Profession, altNumber, aadharNo, societyNmae, ProfilePhoto);
//
//        progressBar.setVisibility(View.VISIBLE);
//final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
//        ApiInterface apiService = retrofit.create(ApiInterface.class);
//
//        Call<UpdateSuccessBean> call = apiService.updateProfileDetails(bean);
//
//        call.enqueue(new Callback<UpdateSuccessBean>() {
//@Override
//public void onResponse(Call<UpdateSuccessBean> call, Response<UpdateSuccessBean> response) {
//        Log.d(TAG, "response=" + response.code());
//        progressBar.setVisibility(View.GONE);
//        if (response.isSuccessful()) {
//
//        String name = response.body().getMemberProfileDtls().getFirstNm() + " " + response.body().getMemberProfileDtls().getLastNm();
//        sessionManager.putPref(Constant.UserName, name, getApplicationContext());
//        sessionManager.putPref(Constant.UserProfile, response.body().getMemberProfileDtls().getImg(), getApplicationContext());
////
//
//        singletonUtil.showSnackBar("Profile updated sucessfully", relativeLayoutParent);
//                       /* Snackbar snackbar = Snackbar
//                                .make(relativeLayoutParent, "Profile updated sucessfully", Snackbar.LENGTH_INDEFINITE)
//                                .setAction("OK", new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        Intent i = new Intent(getApplicationContext(), MainDashboardActivity.class);
//
//                                        startActivity(i);
//                                        finish();
//                                    }
//                                });*/
//
//
//        } else {
//        progressBar.setVisibility(View.GONE);
//        ResponseMessageWrapper error = ErrorUtils.parseError(response, retrofit);
//        // … and use it to show error information
//
//        singletonUtil.showErrorMessage(error, (RelativeLayout) findViewById(R.id.relativeLayoutParent), ProfileActivity.this);
//        // … or just log the issue like we’re doing :)
//
//        }
//
//        }
//
//@Override
//public void onFailure(Call<UpdateSuccessBean> call, Throwable t) {
//        // Log error here since request failed
//        Log.d(TAG, "onFailure: " + t.getMessage());
//        progressBar.setVisibility(View.GONE);
//        singletonUtil.showSnackBar(
//        "Unable to connect to server at this moment!! Please try again later!!",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//
//        }
//        });
//
//
//        }
//
//        }
//
//public void fillProfileDetails(GetProfileBean Bean) {
//
//
//        etFname.setText(Bean.getMemberProfileDtls().getFirstNm());
//        etLname.setText(Bean.getMemberProfileDtls().getLastNm());
//        String name = Bean.getMemberProfileDtls().getFirstNm() + " " + Bean.getMemberProfileDtls().getLastNm();
//        sessionManager.putPref(Constant.UserName, name, getApplicationContext());
//        sessionManager.putPref(Constant.UserProfile, Bean.getMemberProfileDtls().getImg(), getApplicationContext());
//        etMobileNo.setText(Bean.getMemberProfileDtls().getCntcNum());
//        etDOB.setText(singletonUtil.getDate(Bean.getMemberProfileDtls().getDob(), "yyyy-MM-dd hh:mm:ss.SSS").substring(0, 10));
//
//        etSociety.setText(Bean.getMemberProfileDtls().getSocietyNm());
//        if (Bean.getMemberProfileDtls().getAadharCardNo() != null) {
//        etAadhar.setText(Bean.getMemberProfileDtls().getAadharCardNo().toString());
//        }
//        genderCode = Bean.getMemberProfileDtls().getGenderCd();
//        if (Bean.getMemberProfileDtls().getGenderCd() == 20) {
//        etGender.setText("Male");
//        } else {
//        etGender.setText("Female");
//        }
//
//        imageUrl = Bean.getMemberProfileDtls().getImg();
//        if (Bean.getMemberProfileDtls().getAge() != null) {
//        Age = Bean.getMemberProfileDtls().getAge().toString();
//        }
//        if (Bean.getMemberProfileDtls().getAltrntiveCntcNum() != null) {
//        edt_alternate_contact.setText(Bean.getMemberProfileDtls().getAltrntiveCntcNum().toString());
//        }
//        Picasso.with(ProfileActivity.this).load(Bean.getMemberProfileDtls().getImg()).
//        into(image_profile);
//
//
//        ArrayList<String> subProfessionType = new ArrayList<>();
//
//        professionTypeList = Bean.getProfessionTypeDetails();
//        System.out.println("DATA+++>> " + subProfessionType + " **" + professionTypeList);
//
//        if (professionTypeList.size() > 0) {
//        for (int i = 0; i < professionTypeList.size(); i++) {
//        subProfessionType.add(professionTypeList.get(i).getDisplayText());
//
//        }
//        }
//
//        ArrayAdapter<String> homeAdminStringListAdapter = new ArrayAdapter<String>(ProfileActivity.this,
//
//        R.layout.simple_spinner_dropdown_item_profile, subProfessionType);
//
//        Spinnerprofession.setAdapter(homeAdminStringListAdapter);
//
//
//        if (professionTypeList.size() > 0) {
//        for (int i = 0; i < professionTypeList.size(); i++) {
//        if (Bean.getMemberProfileDtls().getProfessionCd() != null) {
//        if (String.valueOf(professionTypeList.get(i).getMasterDataId()) == Bean.getMemberProfileDtls().getProfessionCd().toString()) {
//        VisitId = Bean.getMemberProfileDtls().getProfessionCd().toString();
//        Profession = professionTypeList.get(i).getDisplayText();
////                    Spinnerprofession.setSelection(homeAdminStringListAdapter.getPosition(professionTypeList.get(i).getMasterDataId()));
//        }
//        }
//        }
//        }
//
//        Spinnerprofession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//@Override
//public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        VisitId = professionTypeList.get(i).getMasterDataId().toString();
//        Profession = professionTypeList.get(i).getDisplayText().toString();
//
//        }
//
//@Override
//public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }
//
//        });
//        }
//
//private void callProfileData() {
//
//        progressBar.setVisibility(View.VISIBLE);
//final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
//        ApiInterface apiService = retrofit.create(ApiInterface.class);
//
//        Call<GetProfileBean> call = apiService.getProfileDetails();
//
//        call.enqueue(new Callback<GetProfileBean>() {
//@Override
//public void onResponse(Call<GetProfileBean> call, Response<GetProfileBean> response) {
//        Log.d(TAG, "response=" + response.code());
//        progressBar.setVisibility(View.GONE);
//        if (response.isSuccessful()) {
//        fillProfileDetails(response.body());
//
//        } else {
//        progressBar.setVisibility(View.GONE);
//        ResponseMessageWrapper error = ErrorUtils.parseError(response, retrofit);
//        // … and use it to show error information
//
//        singletonUtil.showErrorMessage(error, (RelativeLayout) findViewById(R.id.relativeLayoutParent), ProfileActivity.this);
//        // … or just log the issue like we’re doing :)
//
//        }
//
//        }
//
//@Override
//public void onFailure(Call<GetProfileBean> call, Throwable t) {
//        // Log error here since request failed
//        Log.d(TAG, "onFailure: " + t.getMessage());
//        progressBar.setVisibility(View.GONE);
//        singletonUtil.showSnackBar(
//        "Unable to connect to server at this moment!! Please try again later!!",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//
//        }
//        });
//
//        }
//
//private void showHideEditTexts(boolean value) {
//        etFname.setFocusableInTouchMode(value);
//        etFname.setFocusable(value);
//        etFname.setCursorVisible(value);
//
//        etLname.setFocusableInTouchMode(value);
//        etLname.setFocusable(value);
//        etLname.setCursorVisible(value);
//
//        etSociety.setFocusableInTouchMode(value);
//        etSociety.setFocusable(value);
//        etSociety.setCursorVisible(value);
//
//        etAadhar.setFocusableInTouchMode(value);
//        etAadhar.setFocusable(value);
//        etAadhar.setCursorVisible(value);
//
//        etGender.setFocusableInTouchMode(value);
//        etGender.setFocusable(value);
//        etGender.setCursorVisible(value);
//        Spinnerprofession.setEnabled(value);
//
//
//        }
//
//
//private void setBackgroundToEdittext2(EditText edittext) {
//        if (Build.VERSION.SDK_INT >= 22)
//        edittext.setBackground(this.getResources().getDrawable(R.drawable.edittext_dark_bottom_border, null));
//        else if (Build.VERSION.SDK_INT >= 16)
//        edittext.setBackground(this.getResources().getDrawable(R.drawable.edittext_dark_bottom_border));
//        else
//        edittext.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.edittext_dark_bottom_border));
//
//        }
//
//private void setBackgroundToEdittext(EditText edittext) {
//        if (Build.VERSION.SDK_INT >= 22)
//        edittext.setBackground(this.getResources().getDrawable(R.drawable.edittext_bottom_border, null));
//        else if (Build.VERSION.SDK_INT >= 16)
//        edittext.setBackground(this.getResources().getDrawable(R.drawable.edittext_bottom_border));
//        else
//        edittext.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.edittext_bottom_border));
//
//        }
//
//
//@Override
//public void onClick(View v) {
//        switch (v.getId()) {
//
//        case R.id.fab_edit:
//        if (isEditable == false) {
//        isEditable = true;
//        showHideEditTexts(true);
//        view_spinner.setVisibility(View.VISIBLE);
//
//        setBackgroundToEdittext(etFname);
//        setBackgroundToEdittext(etLname);
////                    setBackgroundToEdittext(etMobileNo);
//        setBackgroundToEdittext(etSociety);
//        setBackgroundToEdittext(etAadhar);
//        setBackgroundToEdittext(etGender);
//
//        System.out.println("DDD");
//        layout.setVisibility(View.VISIBLE);
////                    img_edit_phone.setVisibility(View.VISIBLE);
//        etGender.setVisibility(View.GONE);
//        img_edit_date.setVisibility(View.VISIBLE);
//        edit_image.setVisibility(View.VISIBLE);
//        edit_image.setOnClickListener(this);
//        img_edit_date.setOnClickListener(this);
//
//
//        } else {
//
//        view_spinner.setVisibility(View.GONE);
//        isEditable = false;
//        showHideEditTexts(false);
//        layout.setVisibility(View.GONE);
//        img_edit_date.setVisibility(View.GONE);
//        edit_image.setVisibility(View.GONE);
//        etGender.setVisibility(View.VISIBLE);
//        img_edit_date.setClickable(false);
//        setBackgroundToEdittext2(etLname);
//        setBackgroundToEdittext2(etFname);
////                    setBackgroundToEdittext(etMobileNo);
//        setBackgroundToEdittext2(etSociety);
//        setBackgroundToEdittext2(etAadhar);
//        setBackgroundToEdittext2(etGender);
//
//        if (singletonUtil.isConnectedToInternet(ProfileActivity.this)) {
//
//        //set profile data
//        updateProfileDetails();
//        } else
//        singletonUtil.showSnackBar(getString(R.string.check_net_connection), relativeLayoutParent);
//
//
//        }
//
//        View view = this.getCurrentFocus();
//        if (view != null) {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//        break;
//
//        case R.id.img_edit_date:
//
//        etDOB.setText("");
//
//        Calendar now = Calendar.getInstance();
//        Calendar calendar = Calendar.getInstance();
//
//        DatePickerDialog dpd = DatePickerDialog.newInstance(
//        ProfileActivity.this,
//        now.get(Calendar.YEAR),
//        now.get(Calendar.MONTH),
//        now.get(Calendar.DAY_OF_MONTH)
//        );
//
//        dpd.setMaxDate(calendar);
//
//        dpd.show(this.getFragmentManager(), "Datepickerdialog");
//        break;
//
//        case R.id.img_edit_phone:
//
//        verifyNumber("Phone");
//        break;
//        case R.id.img_edit_alt_phone:
//
//        verifyNumber("AlternatePhone");
//        break;
//        case R.id.txt_change_password:
//        changePassword();
//        break;
//
//        case R.id.edit_image:
//        selectWindow(Constants.VISITOR_PHOTO_REQ);
//        break;
//
//        }
//        }
//
//
//public void selectWindow(final int requestCode) {
//
//final Dialog mDialog;
//        mDialog = new Dialog(ProfileActivity.this);
//        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        mDialog.setContentView(R.layout.alert2_new);
//        LinearLayout camera, gallery;
//        camera = (LinearLayout) mDialog.findViewById(R.id.layout1);
//        gallery = (LinearLayout) mDialog.findViewById(R.id.layout2);
//        camera.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//        mDialog.dismiss();
//        chooseFromCamera(requestCode);
//        }
//        });
//        gallery.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//        mDialog.dismiss();
//        chooseFromGallery(requestCode);
//        }
//        });
//        mDialog.show();
//        }
//
//public void chooseFromGallery(int requestCode) {
//        imageCrop.setVisibility(View.GONE);
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), requestCode);
//        }
//
//public void chooseFromCamera(int requestCode) {
//        imageCrop.setVisibility(View.GONE);
//        Intent startCustomCameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        file = new File(Environment.getExternalStorageDirectory(),
//        "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
//        uri = Uri.fromFile(file);
//        startCustomCameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
//        startCustomCameraIntent.putExtra("return-data", true);
//        startActivityForResult(startCustomCameraIntent, requestCode);
//        }
//
//
//protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        System.out.println("REQUEST CODE: " + requestCode);
//        if (requestCode == Constants.VISITOR_PHOTO_REQ) {
//        imageCrop.setVisibility(View.GONE);
//        PHOTO_REQ_CODE = requestCode;   // Set which photo needs to be changed
//
//        if (data != null || uri != null) {
//        if (data != null) {
//        ImageCropFunction(data.getData());
//        imageCrop.setVisibility(View.GONE);
//        } else {
//        ImageCropFunction(uri);
//        imageCrop.setVisibility(View.GONE);
//        }
//        } else {
//        imageCrop.setVisibility(View.GONE);
////                Toast.makeText(this, "DEvice is not supportable", Toast.LENGTH_SHORT).show();
//        }
//
//        }
//
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//        CropImage.ActivityResult result = CropImage.getActivityResult(data);
//
//        if (resultCode == RESULT_OK) {
//        Uri resultUri = result.getUri();
//        imageCrop.setVisibility(View.GONE);
//
//        imageCrop.setVisibility(View.GONE);
//        image_profile.setImageURI(resultUri);
//        try {
//        Bitmap imgBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
//        ProfilePhoto = singletonUtil.getEncodedString(imgBitmap);
//
//        } catch (IOException e) {
//        e.printStackTrace();
//        }
//
//        uri = null;
//        }
//
//        }
//
//
//        }
//
//public void ImageCropFunction(Uri uri) {
//        if (uri != null) {
//
//        CropImage.activity(uri)
//        .setGuidelines(CropImageView.Guidelines.ON)
//        .start(this);
//
//        imageCrop.setImageUriAsync(uri);
//        }
//        }
//
//
//@Override
//public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//
//        String dayofmonth = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
//        String monthOfYear1 = monthOfYear++ < 10 ? "0" + monthOfYear++ : "" + monthOfYear++;
//        String date = "" + year + "-" + monthOfYear1 + "-" + dayofmonth;
//        etDOB.setText(date);
//        Log.d("TimePicker", "" + date);
//
//        }
//
//
//public void verifyNumber(String value) {
//
//final AlertDialog.Builder mAlertDialogBuilder = new AlertDialog.Builder(ProfileActivity.this);
//        LayoutInflater inflater = ProfileActivity.this.getLayoutInflater();
//final View mDialogView = inflater.inflate(R.layout.layout_change_phone_no, null);
//        mAlertDialogBuilder.setView(mDialogView);
//final LinearLayout linearLayoutParentDialog = (LinearLayout) mDialogView.findViewById(R.id.linearLayoutParentDialog);
//        TextView btnOk = (TextView) mDialogView.findViewById(R.id.txt_verify);
//final EditText Number = (EditText) mDialogView.findViewById(R.id.editText_contact_no_to_verify);
//
//final AlertDialog alertDialog = mAlertDialogBuilder.create();
//
////        alertDialog.setCancelable(false);
//        alertDialog.show();
//        if (value.equals("Phone")) {
//
//        btnOk.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//        String StringNumber = Number.getText().toString();
//        if (StringNumber != null && StringNumber.length() != 0 && StringNumber.length() == 10) {
//
//
//        if (singletonUtil.isConnectedToInternet(getApplicationContext())) {
//
//        callToSendOtpApiMainContact(StringNumber);
//
//        } else
//        singletonUtil.showSnackBar(getString(R.string.check_net_connection), linearLayoutParentDialog);
//
//        } else {
//        singletonUtil.showSnackBar("Please enter contact no", linearLayoutParentDialog);
//
////                        Toast.makeText(ProfileActivity.this, "Please Enter Contact No", Toast.LENGTH_SHORT).show();
//        }
//
//        alertDialog.dismiss();
//
//        }
//
//        });
//        } else {
//        btnOk.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//        String StringNumber = Number.getText().toString();
//        if (StringNumber != null && StringNumber.length() != 0 && StringNumber.length() == 10) {
//
//
//        if (singletonUtil.isConnectedToInternet(getApplicationContext())) {
//
//        callToSendOtpApi(StringNumber);
//
//        } else
//        singletonUtil.showSnackBar(getString(R.string.check_net_connection), linearLayoutParentDialog);
//
//
//        } else {
//        singletonUtil.showSnackBar("Please enter valid contact no", linearLayoutParentDialog);
//
////                        Toast.makeText(ProfileActivity.this, "Please Enter  Valid Contact No", Toast.LENGTH_SHORT).show();
//        }
//
//        alertDialog.dismiss();
//
//        }
//
//        });
//        }
//
//        }
//
//public void changePassword() {
//
//final AlertDialog.Builder mAlertDialogBuilder = new AlertDialog.Builder(ProfileActivity.this);
//        LayoutInflater inflater = ProfileActivity.this.getLayoutInflater();
//final View mDialogView = inflater.inflate(R.layout.layout_change_password, null);
//        mAlertDialogBuilder.setView(mDialogView);
//final EditText editText_old_password = (EditText) mDialogView.findViewById(R.id.editText_old_password);
//final EditText editText_password = (EditText) mDialogView.findViewById(R.id.editText_password);
//final EditText editText_confirm_password = (EditText) mDialogView.findViewById(R.id.editText_confirm_password);
//        ImageView img_cancel = (ImageView) mDialogView.findViewById(R.id.img_cancel);
//        relativeLayoutParentDialog = (LinearLayout) mDialogView.findViewById(R.id.relativeLayoutParentDialog);
//        TextView btnOk = (TextView) mDialogView.findViewById(R.id.txt_verify);
//
//final AlertDialog alertDialog = mAlertDialogBuilder.create();
//        alertDialog.show();
//
//        img_cancel.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        alertDialog.dismiss();
//        }
//        });
//
//        btnOk.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//final String oldPassword = editText_old_password.getText().toString();
//final String newPassword = editText_password.getText().toString();
//final String confirmPassword = editText_confirm_password.getText().toString();
//
//        if (oldPassword.equals("") || oldPassword.length() < 4) {
//        singletonUtil.showSnackBar("Please enter old Password", relativeLayoutParentDialog);
//        } else if (newPassword == null || newPassword.length() < 4) {
//        singletonUtil.showSnackBar("Please enter valid password", relativeLayoutParentDialog);
//        } else if (!newPassword.equals(confirmPassword)) {
//        singletonUtil.showSnackBar("Please confirm the password", relativeLayoutParentDialog);
//        } else {
//
//
//        if (singletonUtil.isConnectedToInternet(ProfileActivity.this)) {
//        callTochangePassword(oldPassword, newPassword);
//        } else
//        singletonUtil.showSnackBar(getString(R.string.check_net_connection), relativeLayoutParent);
//
//
//        }
//
//        }
//
//        });
//
//        }
//
//
//public void callToAltContact(String otp, String newContact) {
//
//        HashMap<String, String> parameters = new HashMap<>();
//        parameters.put("otp", otp);
//        parameters.put("cntcNum", newContact);
//
//        progressBar.setVisibility(View.VISIBLE);
//final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
//        ApiInterface apiService = retrofit.create(ApiInterface.class);
//
//        Call<ResponseMessageWrapper> call = apiService.changeAltContact(parameters);
//
//        call.enqueue(new Callback<ResponseMessageWrapper>() {
//@Override
//public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
//        Log.d(TAG, "response=" + response.code());
//        progressBar.setVisibility(View.GONE);
//        if (response.isSuccessful()) {
//
//        singletonUtil.showSnackBar(
//        "Alternate Contact changed sucessfully",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
////                    Intent i = new Intent(getApplicationContext(),LoginActivity.class);
////                    startActivity(i);
//
//        } else {
//        progressBar.setVisibility(View.GONE);
//        ResponseMessageWrapper error = ErrorUtils.parseError(response, retrofit);
//        // … and use it to show error information
//
//        singletonUtil.showErrorMessage(error, (RelativeLayout) findViewById(R.id.relativeLayoutParent), ProfileActivity.this);
//        // … or just log the issue like we’re doing :)
//
//        }
//
//        }
//
//@Override
//public void onFailure(Call<ResponseMessageWrapper> call, Throwable t) {
//        // Log error here since request failed
//        Log.d(TAG, "onFailure: " + t.getMessage());
//        progressBar.setVisibility(View.GONE);
//        singletonUtil.showSnackBar(
//        "Unable to connect to server at this moment!! Please try again later!!",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//
//        }
//        });
//        }
//
//public void callToMainContact(String otp, String newContact) {
//
//        HashMap<String, String> parameters = new HashMap<>();
//        parameters.put("otp", otp);
//        parameters.put("cntcNum", newContact);
//
//        progressBar.setVisibility(View.VISIBLE);
//final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
//        ApiInterface apiService = retrofit.create(ApiInterface.class);
//
//        Call<ResponseMessageChangeContact> call = apiService.changeMainContact(parameters);
//
//        call.enqueue(new Callback<ResponseMessageChangeContact>() {
//@Override
//public void onResponse(Call<ResponseMessageChangeContact> call, Response<ResponseMessageChangeContact> response) {
//        Log.d(TAG, "response=" + response.code());
//        progressBar.setVisibility(View.GONE);
//        if (response.isSuccessful()) {
//
//        singletonUtil.showSnackBar(
//        "Contact changed sucessfully",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//        SessionManager sessionManager = new SessionManager(ProfileActivity.this);
//        System.out.println("TOKEN " + response.body().getToken());
//        sessionManager.putPref(Constant.userTokenKey, response.body().getToken(),
//        ProfileActivity.this);
//
//        } else {
//        progressBar.setVisibility(View.GONE);
//        ResponseMessageWrapper error = ErrorUtils.parseError(response, retrofit);
//        // … and use it to show error information
//
//        singletonUtil.showErrorMessage(error, (RelativeLayout) findViewById(R.id.relativeLayoutParent), ProfileActivity.this);
//        // … or just log the issue like we’re doing :)
//
//        }
//
//        }
//
//@Override
//public void onFailure(Call<ResponseMessageChangeContact> call, Throwable t) {
//        // Log error here since request failed
//        Log.d(TAG, "onFailure: " + t.getMessage());
//        progressBar.setVisibility(View.GONE);
//        singletonUtil.showSnackBar(
//        "Unable to connect to server at this moment!! Please try again later!!",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//
//        }
//        });
//        }
//
//
//public void callTochangePassword(String oldPass, String newPass) {
//
//        HashMap<String, String> parameters = new HashMap<>();
//        parameters.put("oldPassword", oldPass);
//        parameters.put("newPassword", newPass);
//
//        progressBar.setVisibility(View.VISIBLE);
//final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
//        ApiInterface apiService = retrofit.create(ApiInterface.class);
//
//        Call<ResponseMessageWrapper> call = apiService.changePassword(parameters);
//
//        call.enqueue(new Callback<ResponseMessageWrapper>() {
//@Override
//public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
//        Log.d(TAG, "response=" + response.code());
//        progressBar.setVisibility(View.GONE);
//        if (response.isSuccessful()) {
//
//        singletonUtil.showSnackBar(
//        "Password changed sucessfully",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//
//        sessionManager.setLogin(false);
//
//        sessionManager.removeKey(Constant.userDtlIdKey);
//        sessionManager.removeKey(Constant.userTokenKey);
//        sessionManager.removeKey(Constant.FlatDetailsID);
//        sessionManager.removeKey(Constant.UserEmail);
//        sessionManager.removeKey(Constant.UserName);
//
//        Intent intent_logout = new Intent(ProfileActivity.this, LoginActivity.class);
//        intent_logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent_logout);
//
//        } else {
//        progressBar.setVisibility(View.GONE);
//        ResponseMessageWrapper error = ErrorUtils.parseError(response, retrofit);
//        // … and use it to show error information
//
//        singletonUtil.showErrorMessage(error, (RelativeLayout) findViewById(R.id.relativeLayoutParent), ProfileActivity.this);
//        // … or just log the issue like we’re doing :)
//
//        }
//
//        }
//
//@Override
//public void onFailure(Call<ResponseMessageWrapper> call, Throwable t) {
//        // Log error here since request failed
//        Log.d(TAG, "onFailure: " + t.getMessage());
//        progressBar.setVisibility(View.GONE);
//        singletonUtil.showSnackBar(
//        "Unable to connect to server at this moment!! Please try again later!!",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//
//        }
//        });
//        }
//
////    {"aadharCardNo":"90968655555","age":"19","altrntiveCntcNum":"","cntcNum":"9970605818","dob":"2017-1-30","firstNm":"Rohan","gender":"Female","genderCd":21,"
//public void otpSendDialog(final String number) {
//
//final AlertDialog.Builder mAlertDialogBuilder =
//        new AlertDialog.Builder(ProfileActivity.this);
//        LayoutInflater inflater = ProfileActivity.this.getLayoutInflater();
//final View mDialogView = inflater.inflate(R.layout.dialog_otp_receive, null);
//        mAlertDialogBuilder.setView(mDialogView);
//        TextView heading1 = (TextView) mDialogView.findViewById(R.id.heading1);
//        heading1.setText("Enter OTP sent to the contact number " + number);
//
//final TextView txtContact = (TextView) mDialogView.findViewById(R.id.txt_contact);
//        Button btnOk = (Button) mDialogView.findViewById(R.id.btnOK);
//        Button btnCancel = (Button) mDialogView.findViewById(R.id.btnCancel);
//final EditText eventName1 = (EditText) mDialogView.findViewById(R.id.edt_visit_purpose);
////        String eventName = eventName1.getText().toString();
//
//final AlertDialog alertDialog = mAlertDialogBuilder.create();
//
////        alertDialog.setCancelable(false);
//        alertDialog.show();
//
//        btnOk.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//        String OTP = txtContact.getText().toString();
//
//        if (OTP == null || OTP.equals("")) {
//        singletonUtil.showSnackBar("Please enter OTP", relativeLayoutParentDialog);
//        } else {
//        callToAltContact(OTP, number);
//
//        alertDialog.dismiss();
//        }
//
//
//        }
//
//        });
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//
////                Toast.makeText(ProfileActivity.this, "Please check number", Toast.LENGTH_SHORT).show();
//
//
//        alertDialog.dismiss();
//        }
//
//        });
//        }
//
//
//public void otpSendDialogForConct(final String number) {
//
//final AlertDialog.Builder mAlertDialogBuilder =
//        new AlertDialog.Builder(ProfileActivity.this);
//        LayoutInflater inflater = ProfileActivity.this.getLayoutInflater();
//final View mDialogView = inflater.inflate(R.layout.dialog_otp_receive, null);
//        mAlertDialogBuilder.setView(mDialogView);
//        TextView heading1 = (TextView) mDialogView.findViewById(R.id.heading1);
//        heading1.setText("Enter OTP sent to the contact number " + number);
//
//final TextView txtContact = (TextView) mDialogView.findViewById(R.id.txt_contact);
//        Button btnOk = (Button) mDialogView.findViewById(R.id.btnOK);
//        Button btnCancel = (Button) mDialogView.findViewById(R.id.btnCancel);
//final EditText eventName1 = (EditText) mDialogView.findViewById(R.id.edt_visit_purpose);
////        String eventName = eventName1.getText().toString();
//
//final AlertDialog alertDialog = mAlertDialogBuilder.create();
//
////        alertDialog.setCancelable(false);
//        alertDialog.show();
//
//        btnOk.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//        String OTP = txtContact.getText().toString();
//
//        if (OTP == null || OTP.equals("")) {
//        singletonUtil.showSnackBar("Please enter OTP", relativeLayoutParentDialog);
//        } else {
//        callToMainContact(OTP, number);
//
//        alertDialog.dismiss();
//        }
//
//
//        }
//
//        });
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//
////                Toast.makeText(ProfileActivity.this, "Please check number", Toast.LENGTH_SHORT).show();
//
//
//        alertDialog.dismiss();
//        }
//
//        });
//        }
//
//private void callToSendOtpApi(final String StringNumber) {
//
//        progressBar.setVisibility(View.VISIBLE);
//
//final Retrofit retrofit = ApiClient.getClient();
//
//        ApiInterface apiService = retrofit.create(ApiInterface.class);
//
//
//        HashMap<String, String> parameters = new HashMap<>();
//        parameters.put("cellnumber", StringNumber);
//
//        Call<ResponseMessageWrapper> call2 = apiService.RequestForOtp(parameters);
//
//        call2.enqueue(new Callback<ResponseMessageWrapper>() {
//@Override
//public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
//        Log.d(TAG, "response=" + response.code());
//        progressBar.setVisibility(View.GONE);
//        if (response.isSuccessful()) {
//        //for response 200
//        Log.d(TAG, "response=" + response.body().toString());
//        otpSendDialog(StringNumber);
////                    showMessageOnOtpSent(response.body(),StringNumber);
//        } else
//        //for response except 200
//        handleErrorResponse(response, retrofit);
//        }
//
//@Override
//public void onFailure(Call<ResponseMessageWrapper> call, Throwable t) {
//        Log.e(TAG, t.toString());
//        progressBar.setVisibility(View.GONE);
//        SingletonUtil.getSingletonConfigInstance().showSnackBar("Unable to connect to server at this moment!! Please try again later!!", (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//
//        }
//        });
//
//        }
//
//
//private void callToSendOtpApiMainContact(final String StringNumber) {
//
//        progressBar.setVisibility(View.VISIBLE);
//
//final Retrofit retrofit = ApiClient.getClient();
//
//        ApiInterface apiService = retrofit.create(ApiInterface.class);
//
//
//        HashMap<String, String> parameters = new HashMap<>();
//        parameters.put("cellnumber", StringNumber);
//
//        Call<ResponseMessageWrapper> call2 = apiService.RequestForOtp(parameters);
//
//        call2.enqueue(new Callback<ResponseMessageWrapper>() {
//@Override
//public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
//        Log.d(TAG, "response=" + response.code());
//        progressBar.setVisibility(View.GONE);
//        if (response.isSuccessful()) {
//        //for response 200
//        Log.d(TAG, "response=" + response.body().toString());
//        otpSendDialogForConct(StringNumber);
////                    showMessageOnOtpSent(response.body(),StringNumber);
//        } else
//        //for response except 200
//        handleErrorResponse(response, retrofit);
//        }
//
//@Override
//public void onFailure(Call<ResponseMessageWrapper> call, Throwable t) {
//        Log.e(TAG, t.toString());
//        progressBar.setVisibility(View.GONE);
//        SingletonUtil.getSingletonConfigInstance().showSnackBar("Unable to connect to server at this moment!! Please try again later!!", (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//
//        }
//        });
//
//        }
//
//
//private void handleErrorResponse(Response<ResponseMessageWrapper> response, Retrofit retrofit) {
//
//        ResponseMessageWrapper error = ErrorUtils.parseError(response, retrofit);
//        // … and use it to show error information
//        if (error.getResponseMessage() != null) {
//        if (error.getResponseMessage().getMessage() != null) {
//        singletonUtil.showSnackBar(error.getResponseMessage().getMessage(), relativeLayoutParent);
//        Log.d("error message", error.getResponseMessage().getMessage());
//        } else
//        singletonUtil.showSnackBar(
//        "Unable to connect to server at this moment!! Please try again later!!",
//        (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//        } else
//
//        SingletonUtil.getSingletonConfigInstance().showSnackBar("Unable to connect to server at this moment!! Please try again later!!", (RelativeLayout) findViewById(R.id.relativeLayoutParent));
//
//        }
//
//
//private void showMessageOnOtpSent(ResponseMessageWrapper body, final String StringNumber) {
//
//        Snackbar snackbar = Snackbar
//        .make((RelativeLayout) findViewById(R.id.relativeLayoutParent), "OTP sent on your contact no", Snackbar.LENGTH_INDEFINITE)
//        .setAction("OK", new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        otpSendDialog(StringNumber);
//        }
//        });
//
//        // Changing message text color
//        snackbar.setActionTextColor(Color.WHITE);
//        // Changing action button text color
//        View sbView = snackbar.getView();
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        snackbar.show();
//
//        }
//
//        }
