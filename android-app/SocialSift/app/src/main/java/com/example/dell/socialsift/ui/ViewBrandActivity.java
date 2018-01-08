package com.example.dell.socialsift.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.socialsift.R;
import com.example.dell.socialsift.adapter.BrandAdapter;
import com.example.dell.socialsift.adapter.PostsAdapter;
import com.example.dell.socialsift.beans.brandBean.Data;
import com.example.dell.socialsift.beans.brandBean.ParticularBrandBean;
import com.example.dell.socialsift.beans.brandBean.Post;
import com.example.dell.socialsift.beans.common.ErrorMessageResponse;
import com.example.dell.socialsift.beans.common.ResponseMessageWrapper;
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

public class ViewBrandActivity extends ParentActivity {
    private RecyclerView recyclerView;
    private BrandAdapter adapter;
    ProgressBar progressBar;
    private SingletonUtil singletonUtil;
    private RelativeLayout parentLayout;
    private ImageView brand_cover;
    private ImageView brand_logo;
    private EditText brand_name;
    private EditText brand_description;
    private Button btnBrandAction, editButton;

    private PostsAdapter postAdapter;
    private SessionManager sessionManager;
    private Button activateDeactivateButton;
    private static final String IMAGE_DIRECTORY = "/sift";
    private String stringBrandProfile = "", stringBrandCover = "", stringBrandName = "" , stringBrandDescription = "";
    private int GALLERY = 1, CAMERA = 2;
    Uri uri;
    Boolean isEditable = false;
String brandName, brandLogo;
    private String serverEmail;
    File file;
    private List<Post> postDataList = new ArrayList<>();
    private ImageView img_editCover, img_editProfile, img_editData;
String brandId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_brand);
        init();
        callForLoadData(getIntent().getStringExtra(Constants.BRAND_ID));
        showHideEditTexts(isEditable);
    }

    private void showHideEditTexts(boolean value) {

        brand_name.setFocusableInTouchMode(value);
        brand_name.setFocusable(value);
        brand_name.setCursorVisible(value);

        brand_description.setFocusableInTouchMode(value);
        brand_description.setFocusable(value);
        brand_description.setCursorVisible(value);
    }

    public void callForLoadData(String brandId) {

        progressBar.setVisibility(View.VISIBLE);

        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<ParticularBrandBean> call = apiService.getBrandDetails(brandId);

        call.enqueue(new Callback<ParticularBrandBean>() {
            @Override
            public void onResponse(Call<ParticularBrandBean> call, Response<ParticularBrandBean> response) {
                Log.d("Vendor", "response=" + response.code());
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
//                    System.out.println("IN SUCCESS");

                    sucessCallSucess(response.body().getData());

                } else {
                    progressBar.setVisibility(View.GONE);
                    ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);


                    singletonUtil.showErrorMessage(error,
                            parentLayout, getApplicationContext());
                }

            }

            @Override
            public void onFailure(Call<ParticularBrandBean> call, Throwable t) {
                // Log error here since request failed
                Log.d("Vendor", "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
                singletonUtil.showSnackBar(
                        "Unable to connect to server at this moment!! Please try again later!!",
                        parentLayout);

            }
        });


    }

    public void sucessCallSucess(Data bean) {
        brandName = bean.getName();
        brandLogo = bean.getBrandLogo();
        Glide.with(getApplicationContext()).load(bean.getCoverPic()).placeholder(R.drawable.bramd_cover).into(brand_cover);
        Glide.with(getApplicationContext()).load(bean.getBrandLogo()).placeholder(R.drawable.bramd_cover).into(brand_logo);
        brand_name.setText(bean.getName());
        brandId = bean.getBrandDtlsId();
        brand_description.setText(bean.getDescription());
        if (bean.getOwnerId().equals(sessionManager.getPref(Constants.USER_ID, getApplicationContext()))) {
            btnBrandAction.setText(Constants.POST);

        } else {
            if (bean.getIsSubscribed() == 1) {
                btnBrandAction.setText(Constants.UNSUBSCRIBE);
            } else {
                btnBrandAction.setText(Constants.SUBSCRIBE);
            }
        }
        if(bean.getPosts().size() > 0 )
        {
            callForSetGrid(bean.getPosts());
        }
        else
        {
            progressBar.setVisibility(View.GONE);
        }
    }
public void callForSetGrid(List<Post> bean)
{


    List<Post> postedList = bean;

    postDataList.addAll(postedList);
    recyclerView.setVisibility(View.VISIBLE);

    postAdapter = new PostsAdapter(getApplicationContext(), postDataList,
            new PostsAdapter.OnItemClickListener() {


                @Override
                public void onItemClick(Post commonModel, int position) {
                    Intent intent = new Intent(getApplicationContext(), ViewBrandActivity.class);
//                    intent.putExtra(Constants.BRAND_ID,commonModel.getBrandDtlsId());
                    startActivity(intent);
                }

            });

    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

    postAdapter.notifyDataSetChanged();


    recyclerView.setAdapter(postAdapter);

    progressBar.setVisibility(View.GONE);
}


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void init() {
        editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
        singletonUtil = SingletonUtil.getSingletonConfigInstance();
        parentLayout = (RelativeLayout) findViewById(R.id.parentLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        sessionManager = new SessionManager(getApplicationContext());
        brand_cover = (ImageView) findViewById(R.id.brand_cover);
        activateDeactivateButton = (Button) findViewById(R.id.activateDeactivateButton);
        activateDeactivateButton.setOnClickListener(this);
        brand_logo = (ImageView) findViewById(R.id.brand_logo);
        img_editCover = (ImageView) findViewById(R.id.img_editCover);
        img_editCover.setOnClickListener(this);
        img_editProfile = (ImageView) findViewById(R.id.img_editProfile);
        img_editProfile.setOnClickListener(this);
        img_editData = (ImageView) findViewById(R.id.img_editData);
        img_editData.setOnClickListener(this);
        brand_name = (EditText) findViewById(R.id.brand_name);
        brand_description = (EditText) findViewById(R.id.brand_description);
        btnBrandAction = (Button) findViewById(R.id.btnBrandAction);
        btnBrandAction.setOnClickListener(this);

       /* ComplaintCategoryMaster2 catMaster = new ComplaintCategoryMaster2("ABC",R.drawable.sport);

        ComplaintCategoryMaster2 catMaster1 = new ComplaintCategoryMaster2("ABC",R.drawable.sport2);

        ComplaintCategoryMaster2 catMaster2 = new ComplaintCategoryMaster2("ABC",R.drawable.sport3);

        ComplaintCategoryMaster2 catMaster3 = new ComplaintCategoryMaster2("ABC",R.drawable.sport);

        ComplaintCategoryMaster2 catMaster4 = new ComplaintCategoryMaster2("ABC",R.drawable.sport2);

        ComplaintCategoryMaster2 catMaster5 = new ComplaintCategoryMaster2("ABC",R.drawable.sport3);

        ComplaintCategoryMaster2 catMaster6 = new ComplaintCategoryMaster2("ABC",R.drawable.sport);

        ComplaintCategoryMaster2 catMaster7 = new ComplaintCategoryMaster2("ABC",R.drawable.sport2);

        list.add(catMaster);
        list.add(catMaster1);
        list.add(catMaster2);
        list.add(catMaster3);
        list.add(catMaster5);
        list.add(catMaster6);
        list.add(catMaster7);
        list.add(catMaster4);

//        }

        System.out.println("LIST IS : " + list);
        adapter = new BrandAdapter(this, list, new BrandAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(ComplaintCategoryMaster2 commonModel, int position) {

                Toast.makeText(getApplicationContext(), " jdhhfdhsfj " , Toast.LENGTH_LONG).show();
                
            }

        

        });
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBrandAction:

                if (btnBrandAction.getText().equals(Constants.POST)) {

                    Intent postIntent = new Intent(getApplicationContext(),PostBrandActivity.class);
                    postIntent.putExtra(Constants.BRAND_NAME,brandName);
                    postIntent.putExtra(Constants.BRAND_LOGO,brandLogo);
                    postIntent.putExtra(Constants.BRAND_ID,brandId);
                    startActivity(postIntent);

                } else if (btnBrandAction.getText().equals(Constants.UNSUBSCRIBE)) {

                    callForUnsubscribeBrand();
                } else if (btnBrandAction.getText().equals(Constants.SUBSCRIBE)) {
                    callForSubscribeBrand();
                }
                break;

            case R.id.activateDeactivateButton:

                if (activateDeactivateButton.getText().equals(Constants.ACTIVATE)) {

                    callForActivateBrand();

                } else {

                    callForDeactivateBrand();
                }
                break;


            case R.id.img_editCover:
                selectWindow(Constants.COVER_PIC_REQ);
                break;

            case R.id.img_editProfile:
                selectWindow(Constants.PROFILE_PIC_REQ);
                break;

            case R.id.img_editData:
                if (isEditable == false) {
                    isEditable = true;
                    showHideEditTexts(isEditable);
                } else {
                    isEditable = false;
                    showHideEditTexts(isEditable);
                }

                break;

            case R.id.editButton:


                if (brand_name.getText().toString() == null || brand_name.getText().toString().equals("")) {
                    singletonUtil.showSnackBar("Please enter brand name", parentLayout);
                } else if (brand_description.getText().toString() == null || brand_description.getText().toString().equals("")) {
                    singletonUtil.showSnackBar("Please enter brand description", parentLayout);
                } else {
                    stringBrandName = brand_name.getText().toString();
                    stringBrandDescription = brand_description.getText().toString();
                    if (singletonUtil.isConnectedToInternet(ViewBrandActivity.this)) {

                        callForUpdateBrand(stringBrandName, stringBrandProfile, stringBrandCover, stringBrandDescription);
                    } else
                        singletonUtil.showSnackBar(getString(R.string.check_net_connection), parentLayout);
                }

                break;
        }
    }


    public void selectWindow(final int requestCode) {

        final Dialog mDialog;
        mDialog = new Dialog(ViewBrandActivity.this);
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

        startActivityForResult(galleryIntent, requestCode);

    }

    public void chooseFromCamera(int requestCode) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, requestCode);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == Constants.COVER_PIC_REQ || requestCode == Constants.PROFILE_PIC_REQ) {
            if (requestCode == Constants.COVER_PIC_REQ) {
                if (data != null) {
                    Uri contentURI = data.getData();
                    try {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        String path = saveImage(bitmap);
                        stringBrandCover = singletonUtil.getEncodedString(bitmap);
                        Toast.makeText(ViewBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                        brand_cover.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ViewBrandActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    brand_cover.setImageBitmap(thumbnail);
                    stringBrandCover = singletonUtil.getEncodedString(thumbnail);
                    saveImage(thumbnail);
                    Toast.makeText(ViewBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
            }

            if (requestCode == Constants.PROFILE_PIC_REQ) {
                if (data != null) {
                    Uri contentURI = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                        String path = saveImage(bitmap);
                        stringBrandProfile = singletonUtil.getEncodedString(bitmap);
                        Toast.makeText(ViewBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                        brand_logo.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ViewBrandActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    brand_logo.setImageBitmap(thumbnail);
                    stringBrandProfile = singletonUtil.getEncodedString(thumbnail);
                    saveImage(thumbnail);
                    Toast.makeText(ViewBrandActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
            }
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


    public void callForSubscribeBrand() {

        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);
        Call<ResponseMessageWrapper> call = apiService.subscribeBrand(brandId);
        call.enqueue(new Callback<ResponseMessageWrapper>() {
            @Override
            public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
                Log.d("Vendor", "response=" + response.code());
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    singletonUtil.showSnackBar(
                            "You have subscribed for brand successfully",
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


    public void callForUnsubscribeBrand() {

        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);
        Call<ResponseMessageWrapper> call = apiService.unsubscribeBrand(brandId);
        call.enqueue(new Callback<ResponseMessageWrapper>() {
            @Override
            public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
                Log.d("Vendor", "response=" + response.code());
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    singletonUtil.showSnackBar(
                            "You have unsubscribe brand successfully",
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

    public void callForActivateBrand() {

        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);
        Call<ResponseMessageWrapper> call = apiService.activateBrand(brandId);
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

    public void callForDeactivateBrand() {

        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);
        Call<ResponseMessageWrapper> call = apiService.deActivateBrand(brandId);
        call.enqueue(new Callback<ResponseMessageWrapper>() {
            @Override
            public void onResponse(Call<ResponseMessageWrapper> call, Response<ResponseMessageWrapper> response) {
                Log.d("Vendor", "response=" + response.code());
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    singletonUtil.showSnackBar(
                            "You have deactivate brand successfully",
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


    public void callForUpdateBrand(String name, String coverPic, String brandLogo, String description) {

        progressBar.setVisibility(View.VISIBLE);
        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("Categories", "");
        parameters.put("coverPic", coverPic);
        parameters.put("brandLogo", brandLogo);
        parameters.put("description", description);
        Call<ResponseMessageWrapper> call = apiService.updateBrand(brandId, parameters);
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

}
