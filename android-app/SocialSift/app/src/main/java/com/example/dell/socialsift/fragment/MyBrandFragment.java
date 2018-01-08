package com.example.dell.socialsift.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.adapter.ApprovedBrandAdapter;
import com.example.dell.socialsift.adapter.NotApprovedBrandAdapter;
import com.example.dell.socialsift.beans.brandBean.DataArr;
import com.example.dell.socialsift.beans.brandBean.MyBrandList;
import com.example.dell.socialsift.beans.common.ErrorMessageResponse;
import com.example.dell.socialsift.ui.CreateBrandActivity;
import com.example.dell.socialsift.ui.SearchBrandActivity;
import com.example.dell.socialsift.ui.ViewBrandActivity;
import com.example.dell.socialsift.utils.commonUtils.Constants;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;
import com.example.dell.socialsift.utils.networking.ApiClientWithToken;
import com.example.dell.socialsift.utils.networking.ApiInterface;
import com.example.dell.socialsift.utils.networking.ErrorUtils;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by DELL on 10/5/2017.
 */

public class MyBrandFragment extends Fragment implements View.OnClickListener {

    private RecyclerView approvedBrandList, nonApprovedBrandList;
    private ApprovedBrandAdapter approvedAdapter;
    private NotApprovedBrandAdapter notApprovedAdapter;
    private FloatingActionButton fabAddBrand, fabSearchBrand;
    private ProgressBar progressBar;
    private SingletonUtil singletonUtil;
    private List<DataArr> approvedDataList = new ArrayList<>();
    private List<DataArr> notApprovedDataList = new ArrayList<>();
    private RelativeLayout approvedLayout, nonApprovedLayout;
    private Button btnAddPost;

    public MyBrandFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_brand, container, false);

        approvedBrandList = (RecyclerView) view.findViewById(R.id.recyclerView_approved_brand);
        nonApprovedBrandList = (RecyclerView) view.findViewById(R.id.recyclerView_non_approved_brand);

        fabAddBrand = (FloatingActionButton) view.findViewById(R.id.menu_add_brand);
        fabAddBrand.setOnClickListener(this);
        fabSearchBrand = (FloatingActionButton) view.findViewById(R.id.menu_search_brand);
        fabSearchBrand.setOnClickListener(this);
        approvedLayout = (RelativeLayout) view.findViewById(R.id.approvedLayout);
        nonApprovedLayout = (RelativeLayout) view.findViewById(R.id.nonApprovedLayout);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        singletonUtil = SingletonUtil.getSingletonConfigInstance();
        btnAddPost = (Button) view.findViewById(R.id.btn_addPost);
        btnAddPost.setOnClickListener(this);
//        init();


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.menu_add_brand:

                Intent addItent = new Intent(getActivity(), CreateBrandActivity.class);
                startActivity(addItent);

                break;
            case R.id.menu_search_brand:

                Intent searchItent = new Intent(getActivity(), SearchBrandActivity.class);
                startActivity(searchItent);

                break;
            case R.id.btn_addPost:
                Intent firstAddItent = new Intent(getActivity(), CreateBrandActivity.class);
                startActivity(firstAddItent);
                break;

        }
    }

    @Override
    public void onResume() {
        callForLoadData();
        super.onResume();
    }

    private void updateUI(List<List<DataArr>> dataDetails) {


        if (approvedDataList.size() > 0 ) {


            approvedLayout.setVisibility(View.VISIBLE);

            approvedAdapter = new ApprovedBrandAdapter(getActivity(), approvedDataList,
                    new ApprovedBrandAdapter.OnItemClickListener() {


                        @Override
                        public void onItemClick(DataArr commonModel, int position) {
                            Intent intent = new Intent(getActivity(), ViewBrandActivity.class);
                            intent.putExtra(Constants.BRAND_ID,commonModel.getBrandDtlsId());
                            startActivity(intent);
                        }
                    });

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
            approvedBrandList.setLayoutManager(mLayoutManager);
            approvedBrandList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

            approvedAdapter.notifyDataSetChanged();


            approvedBrandList.setAdapter(approvedAdapter);
            progressBar.setVisibility(View.GONE);


        }
if(notApprovedDataList.size() > 0)
{
    nonApprovedLayout.setVisibility(View.VISIBLE);
    notApprovedAdapter = new NotApprovedBrandAdapter(getActivity(), notApprovedDataList,
            new NotApprovedBrandAdapter.OnItemClickListener() {


                @Override
                public void onItemClick(DataArr commonModel, int position) {
                    Intent intent = new Intent(getActivity(), ViewBrandActivity.class);
                    intent.putExtra(Constants.BRAND_ID,commonModel.getBrandDtlsId());
                    startActivity(intent);

                }
            });

    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
    nonApprovedBrandList.setLayoutManager(mLayoutManager);
    nonApprovedBrandList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

    notApprovedAdapter.notifyDataSetChanged();


    nonApprovedBrandList.setAdapter(notApprovedAdapter);
}

    }


    public void callForLoadData() {

        progressBar.setVisibility(View.VISIBLE);

        final Retrofit retrofit = ApiClientWithToken.getClient(getActivity());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<MyBrandList> call = apiService.callForGetBrandList();

        call.enqueue(new Callback<MyBrandList>() {
            @Override
            public void onResponse(Call<MyBrandList> call, Response<MyBrandList> response) {
                Log.d("Vendor", "response=" + response.code());
//                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
//                    System.out.println("IN SUCCESS");

                    sucessCallSucess(response.body());

                } else {
                    progressBar.setVisibility(View.GONE);
                    ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);


                    singletonUtil.showErrorMessage(error,
                            getView(), getActivity());
                }

            }

            @Override
            public void onFailure(Call<MyBrandList> call, Throwable t) {
                // Log error here since request failed
                Log.d("Vendor", "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
                singletonUtil.showSnackBar(
                        "Unable to connect to server at this moment!! Please try again later!!",
                        getView());

            }
        });


    }

    public void sucessCallSucess(MyBrandList listBean) {

        if (listBean.getDataArr().get(0).size() > 0 || listBean.getDataArr().get(1).size() > 0) {

            List<DataArr> approvedList = listBean.getDataArr().get(0);

            approvedDataList.addAll(approvedList);

            List<DataArr> notApprovedList = listBean.getDataArr().get(1);

            notApprovedDataList.addAll(notApprovedList);
//            pageNo++;
//        }
            updateUI(listBean.getDataArr());
        } else {
            btnAddPost.setVisibility(View.VISIBLE);
        }

//        System.out.println("FAMILY GUST==>  " + familyGuestVisitDtlList.size());
    }


 /*   public void init(){



        ComplaintCategoryMaster2 catMaster = new ComplaintCategoryMaster2("ABC",R.drawable.sport);

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
        adapter = new BrandAdapter(getActivity(), list, new BrandAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(ComplaintCategoryMaster2 commonModel, int position) {

            }



        });
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        approvedBrandList.setLayoutManager(mLayoutManager);
        approvedBrandList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        approvedBrandList.setAdapter(adapter);

//        nonApprovedBrandList.setLayoutManager(mLayoutManager);
//        nonApprovedBrandList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        nonApprovedBrandList.setAdapter(adapter);
    }*/

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

}


