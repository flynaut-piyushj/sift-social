package com.example.dell.socialsift.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.beans.brandBean.CreateBrandBean;
import com.example.dell.socialsift.beans.common.ErrorMessageResponse;
import com.example.dell.socialsift.beans.common.ResponseMessageWrapper;
import com.example.dell.socialsift.ui.loginUI.LoginActivity;
import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.DatabaseHandler;
import com.example.dell.socialsift.utils.commonUtils.SessionManager;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;
import com.example.dell.socialsift.utils.networking.ApiClientWithToken;
import com.example.dell.socialsift.utils.networking.ApiInterface;
import com.example.dell.socialsift.utils.networking.ErrorUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.view.View.GONE;

/**
 * Created by DELL on 10/4/2017.
 */

public class CreateBrandActivity extends ParentActivity {
    private Button button_create_brand, btn_selectphoto, btn_select_cover_photo;
    private CircleImageView imageViewProfile, imageViewCover;
    private SessionManager sessionManager;
    private DatabaseHandler databaseHandler;
    private static final String IMAGE_DIRECTORY = "/sift";
    private int GALLERY = 1, CAMERA = 2;
    private SingletonUtil singletonUtil;
    private Uri uri;
    private File file;
    private ProgressBar progressBar;
    private RelativeLayout parentLayout;
    private EditText editTextBrandName;
    private Spinner spinner_category;
    private String profileImage, coverImage;
    private static int PHOTO_REQ_CODE = -999;
    private EditText editTextBrandDescription;
    private Integer checkCategory = 0;
    List<Integer> selectedCategoryList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_brand);
        init();
        setSpinner();

    }

    public void init() {
        button_create_brand = (Button) findViewById(R.id.button_create_brand);
        btn_selectphoto = (Button) findViewById(R.id.btn_selectphoto);
        btn_select_cover_photo = (Button) findViewById(R.id.btn_select_cover_photo);
        button_create_brand.setOnClickListener(this);
        btn_selectphoto.setOnClickListener(this);
        btn_select_cover_photo.setOnClickListener(this);
        databaseHandler = new DatabaseHandler(getApplicationContext());
        singletonUtil = SingletonUtil.getSingletonConfigInstance();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        parentLayout = (RelativeLayout) findViewById(R.id.parentLayout);
        editTextBrandName = (EditText) findViewById(R.id.editTextBrandName);
        spinner_category = (Spinner) findViewById(R.id.spinner_category);
        imageViewProfile = (CircleImageView) findViewById(R.id.profile_image);
        imageViewCover = (CircleImageView) findViewById(R.id.cover_image);
        editTextBrandDescription = (EditText) findViewById(R.id.editTextBrandDescription);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.button_create_brand:
                String name = editTextBrandName.getText().toString();
                String description = editTextBrandDescription.getText().toString();
                if (validate()) {
                    showHideKeyboard();
                    if (profileImage == null || profileImage.equals("")) {
                        singletonUtil.showSnackBar("Please select profile photo", parentLayout);
                    } else if (coverImage == null || coverImage.equals("")) {
                        singletonUtil.showSnackBar("Please select cover photo", parentLayout);
                    } else {

                        if (singletonUtil.isConnectedToInternet(CreateBrandActivity.this)) {
                            CreateBrandBean bean = new CreateBrandBean(name, selectedCategoryList, profileImage, coverImage, description);
                            callForBrandCreation(bean);

                        } else
                            singletonUtil.showSnackBar(getString(R.string.check_net_connection), parentLayout);
                    }
                } else
                    singletonUtil.showSnackBar("Please provide valid details", parentLayout);


                break;

            case R.id.btn_selectphoto:
                selectWindow(Constants.PROFILE_PIC_REQ);
                break;

            case R.id.btn_select_cover_photo:
                selectWindow(Constants.COVER_PIC_REQ);
                break;


        }
    }


    public void setSpinner() {

        List<String> categoryNameList = new ArrayList<String>();

        categoryNameList.add("Select Category");
        for (int i = 0; i < databaseHandler.getCategoryList().size(); i++) {

            categoryNameList.add(databaseHandler.getCategoryList().get(i));

        }

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(CreateBrandActivity.this,

                R.layout.simple_spinner_dropdown_item, categoryNameList);

        spinner_category.setAdapter(categoryAdapter);


        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                checkCategory++;


                if (checkCategory > 1) {
                    if (parent.getSelectedItem().toString().equals("Select Category")) {
                        singletonUtil.showSnackBar("Please select category", parentLayout);

                        final List<String> stateNameList = new ArrayList<String>();
                    } else {


                        String categoryID = databaseHandler.getCategoryID(parent.getItemAtPosition(pos).toString());
                        if(selectedCategoryList.size()<2)
                        selectedCategoryList.add(Integer.parseInt(categoryID));
                        else
                            singletonUtil.showSnackBar("You can select only three categories", parentLayout);



                    }
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }



    public void callForBrandCreation(CreateBrandBean bean) {

        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<ResponseMessageWrapper> call = apiService.callBrandCreation(bean);

        call.enqueue(new Callback<ResponseMessageWrapper>() {
            @Override
            public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
                Log.d("Vendor", "response=" + response.code());
                progressBar.setVisibility(GONE);
                if (response.isSuccessful()) {


                    singletonUtil.showSnackBar("registration completed!!!", parentLayout);
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);


                } else {
                    progressBar.setVisibility(GONE);
                    ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);


                    singletonUtil.showErrorMessage(error,
                            parentLayout, getApplicationContext());

                }

            }

            @Override
            public void onFailure(Call<ResponseMessageWrapper> call, Throwable t) {
                // Log error here since request failed
                Log.d("Vendor", "onFailure: " + t.getMessage());
                progressBar.setVisibility(GONE);


                singletonUtil.showSnackBar(
                        "Unable to connect to server at this moment!! Please try again later!!",
                        parentLayout);

            }
        });


    }


    public void selectWindow(final int requestCode) {

        final Dialog mDialog;
        mDialog = new Dialog(CreateBrandActivity.this);
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

        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), requestCode);

    }

    public void chooseFromCamera(int requestCode) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, requestCode);

    }

    private boolean validate() {
        boolean valid = true;

        String name = editTextBrandName.getText().toString();
        String description = editTextBrandDescription.getText().toString();


        if (name.isEmpty() || name.length() < 4) {
            valid = false;
            showErrorInUi(editTextBrandName, Html.fromHtml("<font color='red'>Enter brand name</font>"));

        } else
            editTextBrandName.setError(null);

        if (description.isEmpty() || description.length() < 10) {
            valid = false;
            showErrorInUi(editTextBrandDescription, Html.fromHtml("<font color='red'>Enter brand description</font>"));

        } else
            editTextBrandDescription.setError(null);


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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        PHOTO_REQ_CODE = requestCode;
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        switch (PHOTO_REQ_CODE) {
            case Constants.PROFILE_PIC_REQ:
                imageViewCover.setVisibility(View.VISIBLE);
                imageViewProfile.setVisibility(View.VISIBLE);

                if (data != null && data.getData() != null) {
                    Uri contentURI = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        String path = saveImage(bitmap);
                        profileImage = singletonUtil.getEncodedString(bitmap);
                        Toast.makeText(CreateBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                        imageViewProfile.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(CreateBrandActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {


                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageViewProfile.setImageBitmap(thumbnail);
                    profileImage = singletonUtil.getEncodedString(thumbnail);
                    saveImage(thumbnail);
                    Toast.makeText(CreateBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }

               /* if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            profileImage = singletonUtil.getEncodedString(bitmap);
                            Toast.makeText(CreateBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                            imageViewProfile.setImageBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(CreateBrandActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageViewProfile.setImageBitmap(thumbnail);
                    profileImage = singletonUtil.getEncodedString(thumbnail);
                    saveImage(thumbnail);
                    Toast.makeText(CreateBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }*/
                break;
            case Constants.COVER_PIC_REQ:

                imageViewCover.setVisibility(View.VISIBLE);
                imageViewProfile.setVisibility(View.VISIBLE);


                if (data != null && data.getData() != null) {
                    Uri contentURI = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        String path = saveImage(bitmap);
                        coverImage = singletonUtil.getEncodedString(bitmap);
                        Toast.makeText(CreateBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                        imageViewCover.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(CreateBrandActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageViewCover.setImageBitmap(thumbnail);
                    coverImage = singletonUtil.getEncodedString(thumbnail);
                    saveImage(thumbnail);
                    Toast.makeText(CreateBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
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


}
