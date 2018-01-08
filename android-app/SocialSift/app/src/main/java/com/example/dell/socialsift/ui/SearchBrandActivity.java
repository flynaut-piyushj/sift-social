package com.example.dell.socialsift.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.adapter.RandomBrandAdapter;
import com.example.dell.socialsift.beans.brandBean.Datum;
import com.example.dell.socialsift.beans.brandBean.RandomBrandList;
import com.example.dell.socialsift.beans.common.ErrorMessageResponse;
import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.DatabaseHandler;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;
import com.example.dell.socialsift.utils.networking.ApiClientWithToken;
import com.example.dell.socialsift.utils.networking.ApiInterface;
import com.example.dell.socialsift.utils.networking.ErrorUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by DELL on 10/12/2017.
 */

public class SearchBrandActivity extends ParentActivity {
    private AutoCompleteTextView autoCompleteTextCategory;
    private RecyclerView brandList;
    private RandomBrandAdapter adapter;
    ProgressBar progressBar;
    private SingletonUtil singletonUtil;
    private LinearLayout parentLayout;
    private DatabaseHandler databaseHandler;
    List<Datum> dataList;
    String categoryName;
    private Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_brand);
        init();
    }
    private void init(){
        databaseHandler = new DatabaseHandler(getApplicationContext());
        autoCompleteTextCategory = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        autoCompleteTextCategory.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                searchForResult(s.toString());

            }
        });

        brandList = (RecyclerView)findViewById(R.id.recyclerView_brand);
        brandList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        brandList.setNestedScrollingEnabled(false);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        parentLayout = (LinearLayout)findViewById(R.id.parentLayout);
        singletonUtil = SingletonUtil.getSingletonConfigInstance();
        callForLoadData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:

                callForLoadDataByCategory();

                break;
        }
    }

    public void searchForResult(String s) {
        final List<String> categoriesArray = new ArrayList<>();
        categoriesArray.addAll(databaseHandler.getCategoryList());
//               String stringToAdd = null;
//
//        for (int i = 0; i < hostFamily.getFlatDetailsLookUpList().size(); i++) {
//
//            String Name = hostFamily.getFlatDetailsLookUpList().get(i).getMemberNm();
//            if (Name != null) {
//                stringToAdd = Name + ", " + hostFamily.getFlatDetailsLookUpList().get(i).getFlatDtls();
//                hostFamilyArrayList.add(stringToAdd);
//            } else {
//                stringToAdd = hostFamily.getFlatDetailsLookUpList().get(i).getFlatDtls();
//                hostFamilyArrayList.add(stringToAdd);
////                hostFamilyArrayList.add(hostFamily.getFlatDetailsLookUpList().get(i).getFlatDtls());
//            }
//
//            System.out.println("HOST LIST==> " + hostFamily.getFlatDetailsLookUpList().get(i).getMemberNm() + " ," + hostFamily.getFlatDetailsLookUpList().get(i).getFlatDtls());
//        }
//        System.out.println("HOST LIST11111111==> " + hostFamilyArrayList);


        ArrayAdapter<String> autocompleteAdapter = new ArrayAdapter<String>
                (SearchBrandActivity.this, android.R.layout.simple_dropdown_item_1line,
                        categoriesArray);

        autoCompleteTextCategory.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        autoCompleteTextCategory.setAdapter(autocompleteAdapter);


//        final String finalStringToAdd = stringToAdd;
        autoCompleteTextCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                categoryName = categoriesArray.get(pos);
                autoCompleteTextCategory.setText(categoriesArray.get(pos));
                categoriesArray.clear();

                /*if (hostFamily.getFlatDetailsLookUpList().get(pos).getMemberNm() != null) {
                    addressArrayList.add(new AddressList(hostFamily.getFlatDetailsLookUpList().get(pos).getMemberNm()
                            + ", " + hostFamily.getFlatDetailsLookUpList().get(pos).getFlatDtls(), ""));
                } else {
                    addressArrayList.add(new AddressList(hostFamily.getFlatDetailsLookUpList().get(pos).getFlatDtls(), ""));
                }

                showHostAddressList(addressArrayList);
                System.out.println("SIZE OF ARRAY==>  " + addressArrayList.size());
//                addressArrayList.add(new AddressList(adapterView.getItemAtPosition(pos).toString()));

                FlatID = hostFamily.getFlatDetailsLookUpList().get(pos).getFlatDtlsId().toString();

                UnRegisterFlatDtl flatDtls = new UnRegisterFlatDtl(hostFamily.getFlatDetailsLookUpList().get(pos).getFlatDtlsId().toString());
                flatDtl.add(flatDtls);
                System.out.println("HOST FAMILY DETAILS  " + flatDtl.toString());*/


            }
        });


    }


    private void updateUI(List<Datum> dataDetails) {
        if (dataList.size() > 0) {


            brandList.setVisibility(View.VISIBLE);
//            textViewEmptyMessage.setVisibility(View.GONE);



            adapter = new RandomBrandAdapter(getApplicationContext(), dataList,
                    new RandomBrandAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Datum commonModel, int position) {
                            Intent intent = new Intent(getApplicationContext(), ViewBrandActivity.class);
                            intent.putExtra(Constants.BRAND_ID,commonModel.getBrandDtlsId());
                            startActivity(intent);
                        }


                    });

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            brandList.setLayoutManager(mLayoutManager);
            brandList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

            adapter.notifyDataSetChanged();




            brandList.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);



        } else {

            brandList.setVisibility(View.GONE);


        }

    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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

    public void callForLoadDataByCategory() {

        progressBar.setVisibility(View.VISIBLE);
        String categoryId = databaseHandler.getCategoryID(categoryName);

        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<RandomBrandList> call = apiService.getDataByCategory(categoryId);

        call.enqueue(new Callback<RandomBrandList>() {
            @Override
            public void onResponse(Call<RandomBrandList> call, Response<RandomBrandList> response) {
                Log.d("Vendor", "response=" + response.code());
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
//                    System.out.println("IN SUCCESS");

                    sucessCallSucess(response.body());

                } else {
                    progressBar.setVisibility(View.GONE);
                    ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);


                    singletonUtil.showErrorMessage(error,
                            parentLayout, getApplicationContext());
                }

            }

            @Override
            public void onFailure(Call<RandomBrandList> call, Throwable t) {
                // Log error here since request failed
                Log.d("Vendor", "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
                singletonUtil.showSnackBar(
                        "Unable to connect to server at this moment!! Please try again later!!",
                        parentLayout);

            }
        });


    }


    public void callForLoadData() {

        progressBar.setVisibility(View.VISIBLE);

        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<RandomBrandList> call = apiService.callForGetRandomList();

        call.enqueue(new Callback<RandomBrandList>() {
            @Override
            public void onResponse(Call<RandomBrandList> call, Response<RandomBrandList> response) {
                Log.d("Vendor", "response=" + response.code());
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
//                    System.out.println("IN SUCCESS");

                    sucessCallSucess(response.body());

                } else {
                    progressBar.setVisibility(View.GONE);
                    ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);


                    singletonUtil.showErrorMessage(error,
                            parentLayout, getApplicationContext());
                }

            }

            @Override
            public void onFailure(Call<RandomBrandList> call, Throwable t) {
                // Log error here since request failed
                Log.d("Vendor", "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
                singletonUtil.showSnackBar(
                        "Unable to connect to server at this moment!! Please try again later!!",
                        parentLayout);

            }
        });


    }

    public void sucessCallSucess(RandomBrandList randomListBean) {

        if (randomListBean.getData().size() > 0) {
//    progressBar.setVisibility(View.VISIBLE);


            List<Datum> randomList = randomListBean.getData();
//        for (int i = 0; i < familyGuest.size(); i++) {
            dataList = new ArrayList<>();
            dataList.addAll(randomList);
//            pageNo++;
//        }
            updateUI(randomListBean.getData());
        }

//        System.out.println("FAMILY GUST==>  " + familyGuestVisitDtlList.size());
    }

}
