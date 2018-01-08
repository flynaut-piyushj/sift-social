package com.example.dell.socialsift.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.dell.socialsift.R;
import com.example.dell.socialsift.beans.brandBean.SendPostBean;
import com.example.dell.socialsift.beans.common.ErrorMessageResponse;
import com.example.dell.socialsift.beans.common.ResponseMessageWrapper;
import com.example.dell.socialsift.utils.commonUtils.Base64;
import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.SessionManager;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;
import com.example.dell.socialsift.utils.networking.ApiClientWithToken;
import com.example.dell.socialsift.utils.networking.ApiInterface;
import com.example.dell.socialsift.utils.networking.ErrorUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by DELL on 10/13/2017.
 */

public class PostBrandActivity extends ParentActivity {
    private TextView txt_brand_name;
    private ImageView brand_logo;
    private Button buttonPost;
    private LinearLayout layout_photos, layout_video;
    private static final String IMAGE_DIRECTORY = "/sift";
    private int GALLERY = 1, CAMERA = 2;
    Uri uri;
    private EditText txtPost;
    private RelativeLayout parentLayout;
    private SessionManager sessionManager;
    private VideoView videoPreview;
    private String stringImage, stringVideo;
    private ImageView imagePreview;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private SingletonUtil singletonUtil;
    private ProgressBar progressBar;
    String brandId;
    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    List<String> imageList = new ArrayList<>();
    List<String> vedioList = new ArrayList<>();
    private Uri fileUri; // file url to store image/video

    private ImageView imgPreview;


    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_brand);
        init();
        setData();
    }

    public void init() {
        txt_brand_name = (TextView) findViewById(R.id.txt_brand_name);
        brand_logo = (ImageView) findViewById(R.id.brand_logo);
        brandId = getIntent().getStringExtra(Constants.BRAND_ID);
        buttonPost = (Button) findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(this);
        layout_photos = (LinearLayout) findViewById(R.id.layout_photos);
        layout_photos.setOnClickListener(this);
        layout_video = (LinearLayout) findViewById(R.id.layout_video);
        layout_video.setOnClickListener(this);
        parentLayout = (RelativeLayout) findViewById(R.id.parentLayout);
        sessionManager = new SessionManager(getApplicationContext());
        videoPreview = (VideoView) findViewById(R.id.videoPreview);
        imagePreview = (ImageView) findViewById(R.id.imagePreview);
        singletonUtil = SingletonUtil.getSingletonConfigInstance();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        txtPost = (EditText) findViewById(R.id.txtPost);
        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
    }

    /**
     * Previewing recorded video
     */
    private void previewVideo() {
        try {
            // hide image preview
//            imgPreview.setVisibility(View.GONE);
//
//            videoPreview.setVisibility(View.VISIBLE);
            videoPreview.setVideoPath(fileUri.getPath());
            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(fileUri.getPath().toString(), MediaStore.Video.Thumbnails.MINI_KIND);
            imagePreview.setImageBitmap(thumb);
            stringVideo = Base64.encodeFromFile(fileUri.toString());
            System.out.println("stringVideo  " + stringVideo);
            vedioList.add(stringVideo);
            // start playing
            videoPreview.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPost:
                SendPostBean bean = new SendPostBean(txtPost.getText().toString(), imageList, vedioList);

                callForPost(bean);
                break;
            case R.id.layout_photos:

                selectWindow(Constants.IMAGE_REQ);
                break;
            case R.id.layout_video:
                selectWindow(Constants.VEDIO_REQ);

                break;


        }
    }


    public void callForPost(SendPostBean bean) {

        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);


        Call<ResponseMessageWrapper> call = apiService.postOnBrand(brandId, bean);
        call.enqueue(new Callback<ResponseMessageWrapper>() {
            @Override
            public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
                Log.d("Vendor", "response=" + response.code());
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    singletonUtil.showSnackBar(
                            "You have activate brand successfully",
                            parentLayout);
                } else {
                    progressBar.setVisibility(View.GONE);
                    ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);
                    singletonUtil.showErrorMessage(error,
                            parentLayout, getApplicationContext());
                }
            }

            @Override
            public void onFailure(Call<ResponseMessageWrapper> call, Throwable t) {
                // Log error here since request failed
                Log.d("Vendor", "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
                singletonUtil.showSnackBar(
                        "Unable to connect to server at this moment!! Please try again later!!",
                        parentLayout);
            }
        });
    }


    public void selectWindow(final int requestCode) {

        final Dialog mDialog;
        mDialog = new Dialog(PostBrandActivity.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_select_media_for_image);
        LinearLayout camera, gallery;
        camera = (LinearLayout) mDialog.findViewById(R.id.layout1);
        gallery = (LinearLayout) mDialog.findViewById(R.id.layout2);
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                if (requestCode == Constants.IMAGE_REQ)
                    chooseFromCamera(requestCode);
                else
                    chooseVedioFromCamera(requestCode);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                if (requestCode == Constants.IMAGE_REQ)
                    chooseFromGallery(requestCode);
                else
                    chooseVedioFromGallery(requestCode);
            }
        });
        mDialog.show();
    }

    public void chooseFromGallery(int requestCode) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, requestCode);

    }

    public void chooseFromCamera(int requestCode) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, requestCode);

    }

    private void chooseVedioFromCamera(int requestCode) {


        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

        // set video quality
        // 1- for high quality video
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // start the video capture Intent
        startActivityForResult(intent, requestCode);
    }

    public void chooseVedioFromGallery(int requestCode) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), requestCode);

    }


    /**
     * Create a file Uri for saving an image or video
     */
    private static Uri getOutputMediaFileUri(int type) {

        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {

        // Check that the SDCard is mounted
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Sift");


        // Create the storage directory(MyCameraVideo) if it does not exist
        if (!mediaStorageDir.exists()) {

            if (!mediaStorageDir.mkdirs()) {

//                output.setText("Failed to create directory MyCameraVideo.");
//                ApplicationContext(), "Failed to create directory MyCameraVideo.",
//                        Toast.LENGTH_LON
//                Toast.makeText(getAppG).show();

                Log.d("MyCameraVideo", "Failed to create directory MyCameraVideo.");
                return null;
            }
        }

        // Create a media file name

        // For unique file name appending current timeStamp with file name
        java.util.Date date = new java.util.Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(date.getTime());

        File mediaFile;

        if (type == MEDIA_TYPE_VIDEO) {

            // For unique video file name appending current timeStamp with file name


            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");

        } else {
            return null;
        }

        return mediaFile;
    }





/*
    public Uri getOutputMediaFileUri(int type) {

        return FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".my.package.name.provider", getOutputMediaFile());
//        return Uri.fromFile(getOutputMediaFile(type));
    }


    */

    /**
     * returning image / video
     *//*

    private static File getOutputMediaFile() {



        File videoStorage = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "sift");

        if (!videoStorage.exists()) {
            if (!videoStorage.mkdirs()) {
//                Toast.makeText(getApplicatio, "Failed to create Video directory .", Toast.LENGTH_LONG).show();
                Log.d("SurveySignalVideos", "Failed " +
                        "to create Video directory .");
                return null;
            }

        }
        Date date = new java.util.Date();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date.getTime());

        File mediaFile = new File(videoStorage.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
        System.out.println("ChatScreenFragment.getOutputMediaFile()" + mediaFile.toString());
*/
/*

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }
*//*


        return mediaFile;
    }
*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == Constants.IMAGE_REQ) {
            if (data.getData() != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    stringImage = singletonUtil.getEncodedString(bitmap);
                    imageList.add(stringImage);
                    Toast.makeText(PostBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imagePreview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(PostBrandActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                imagePreview.setImageBitmap(thumbnail);
                stringImage = singletonUtil.getEncodedString(thumbnail);
                imageList.add(stringImage);
                saveImage(thumbnail);
                Toast.makeText(PostBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == Constants.VEDIO_REQ) {
//            if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // video successfully recorded
                // preview the recorded video
                previewVideo();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
//            }
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

    /**
     * Checking device has camera hardware or not
     */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public void setData() {
        txt_brand_name.setText(getIntent().getStringExtra(Constants.BRAND_NAME));
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra(Constants.BRAND_LOGO)).placeholder(R.drawable.logo_brand).into(brand_logo);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    /*
     * Here we restore the fileUri again
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }
}
