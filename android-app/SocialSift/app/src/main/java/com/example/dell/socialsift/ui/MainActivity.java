package com.example.dell.socialsift.ui;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.fragment.PeopleFragment;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 8/23/2017.
 */

public class MainActivity extends ParentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    ProgressBar progressBar;


    private RecyclerView recyclerView,recyclerView2;
    private CategoryAdapter adapter;
    private Toolbar toolbar;
    List<ComplaintCategoryMaster> list = new ArrayList<ComplaintCategoryMaster>();
//    private List<Data> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setToolbar("");
    }

    public void setToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_nav);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        TextView mToolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
//        mToolbarTitle.setText(s);
    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setViewPager();


        System.out.println("IN ON CREATE");
//        setToolbar();
//        getData();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void setViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        Fragment fragmentEventDetails = new PeopleFragment();
//
        adapter.addFragment(fragmentEventDetails,"People(600)");

        Fragment fragmentEventPrizes = new PeopleFragment();

        adapter.addFragment(fragmentEventPrizes, "Brand(1300)");



//        Fragment fragmentGuest = new EventDetailsFragment();
//        adapter.addFragment(fragmentGuest, "Family Guest (");
//
//
//        Fragment fragmentVendor = new EventDetailsFragment();
//        adapter.addFragment(fragmentVendor, "Society Vendor )");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);



    }


  /*  public void init(){


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        ComplaintCategoryMaster catMaster = new ComplaintCategoryMaster("ABC",R.drawable.sport);

        ComplaintCategoryMaster catMaster1 = new ComplaintCategoryMaster("ABC",R.drawable.sport2);

        ComplaintCategoryMaster catMaster2 = new ComplaintCategoryMaster("ABC",R.drawable.sport3);

        ComplaintCategoryMaster catMaster3 = new ComplaintCategoryMaster("ABC",R.drawable.sport);

        ComplaintCategoryMaster catMaster4 = new ComplaintCategoryMaster("ABC",R.drawable.sport2);

        ComplaintCategoryMaster catMaster5 = new ComplaintCategoryMaster("ABC",R.drawable.sport3);

        ComplaintCategoryMaster catMaster6 = new ComplaintCategoryMaster("ABC",R.drawable.sport);

        ComplaintCategoryMaster catMaster7 = new ComplaintCategoryMaster("ABC",R.drawable.sport2);

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
        adapter = new CategoryAdapter(this, list, new CategoryAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(ComplaintCategoryMaster commonModel, int position) {

            }



        });
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


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
    }*/
}

