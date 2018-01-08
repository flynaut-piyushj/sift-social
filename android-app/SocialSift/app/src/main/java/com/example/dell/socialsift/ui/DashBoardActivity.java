package com.example.dell.socialsift.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.fragment.PeopleFragment;
import com.example.dell.socialsift.utils.commonUtils.SingletonUtil;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private String TAG = "MainDashboardActivity";

    private NavigationView navigationView;

    private FrameLayout framelayoutParent;

    private Dialog dialog;
    private DrawerLayout drawer;
    private SingletonUtil singletonUtil;
    private FrameLayout layoutViewDeals;
    private FrameLayout layoutViewEvents;
    private FrameLayout layoutViewRequest;
    private FrameLayout layoutSeekGolfer;
    private TextView budget;
    private Button buttonPost;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ProgressBar progressBar;

    private RecyclerView recyclerView, recyclerView2;
    private CategoryAdapter adapter;
    private Toolbar toolbar;
    List<ComplaintCategoryMaster> list = new ArrayList<ComplaintCategoryMaster>();
//    private List<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        init();

    }


    public void init() {
        setToolbar();
        getData();
        buttonPost = (Button) findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        ComplaintCategoryMaster catMaster = new ComplaintCategoryMaster("ABC", R.drawable.sport);

        ComplaintCategoryMaster catMaster1 = new ComplaintCategoryMaster("ABC", R.drawable.sport2);

        ComplaintCategoryMaster catMaster2 = new ComplaintCategoryMaster("ABC", R.drawable.sport3);

        ComplaintCategoryMaster catMaster3 = new ComplaintCategoryMaster("ABC", R.drawable.sport);

        ComplaintCategoryMaster catMaster4 = new ComplaintCategoryMaster("ABC", R.drawable.sport2);

        ComplaintCategoryMaster catMaster5 = new ComplaintCategoryMaster("ABC", R.drawable.sport3);

        ComplaintCategoryMaster catMaster6 = new ComplaintCategoryMaster("ABC", R.drawable.sport);

        ComplaintCategoryMaster catMaster7 = new ComplaintCategoryMaster("ABC", R.drawable.sport2);

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
    }

//    private void init() {
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//        setViewPager();
//
//
//        System.out.println("IN ON CREATE");
//        setToolbar();
//        getData();
//    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            //call onBackPressed() on pressing home button(so that it works same as hardware back button)
            case android.R.id.home:
                final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                break;

//            case R.id.action_logout:
//                Toast.makeText(getApplicationContext(),"Hellooo", Toast.LENGTH_LONG).show();
//
//                break;


        }

        return true;
    }


    public void setToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_nav);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);





      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar_dashboard);
//        toolbar_dashboard.setNavigationIcon(R.drawable.ic_menu);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageView mToolbarTitle = (ImageView) toolbar.findViewById(R.id.ic_menu);
        mToolbarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });*/


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

        DashBoardActivity.ViewPagerAdapter adapter = new DashBoardActivity.ViewPagerAdapter(getSupportFragmentManager());


        Fragment fragmentEventDetails = new PeopleFragment();
//
        adapter.addFragment(fragmentEventDetails, "People(600)");

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPost:
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);

                startActivity(intent);


                break;
        }
    }


    public void getData() {
//

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

//                Intent intent = new Intent(MainActivity16.this, ProfileActivity.class);
//                startActivity(intent);

            }
        });

    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {


            case R.id.nav_subcribers:

                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i1);

                break;

            case R.id.nav_explore:
                Intent i2 = new Intent(getApplicationContext(), NewExploreActivity.class);
                startActivity(i2);

                break;

            case R.id.nav_about:
                Intent i3 = new Intent(getApplicationContext(), AccountSettingActivity.class);
                startActivity(i3);

                break;

            case R.id.nav_brands:
                Intent i4 = new Intent(getApplicationContext(), BrandActivity.class);
                startActivity(i4);

                break;
            case R.id.nav_pre_profile:
                Intent i5 = new Intent(getApplicationContext(), PreProfileActivity.class);
                startActivity(i5);

                break;


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//
//
//            case R.id.nav_requests:
//                Intent request_intent = new Intent(getApplicationContext(), ViewRequestActivity.class);
//                startActivity(request_intent);
//                break;
//
//            case R.id.nav_events:
//                Intent event_intent = new Intent(getApplicationContext(), DetailEventActivity.class);
//                startActivity(event_intent);
//                break;
//
//            case R.id.nav_about:
//                Intent about_intent = new Intent(getApplicationContext(), AboutActivity.class);
//                startActivity(about_intent);
//                break;
//
//            case R.id.nav_setting:
//                Intent setting_intent = new Intent(getApplicationContext(), SettingActivity.class);
//                startActivity(setting_intent);
//                break;
//
//            case R.id.nav_help:
//                Intent help_intent = new Intent(getApplicationContext(), HelpActivity.class);
//                startActivity(help_intent);
//                break;
//
//
//        }
//
//        item.setChecked(true);
//        drawer.closeDrawers();
//        return true;
//    }


/*    private void callToUpdateNotificationStatus() {


        final Retrofit retrofit = ApiClientWithToken.getClient(getApplicationContext());
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<ResponseMessage> call = apiService.upadteNotificationStatus(0);

        call.enqueue(new Callback<ResponseMessage>() {
                         @Override
                         public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                             Log.d(TAG, "response=" + response.code());

                             if (response.isSuccessful()) {

                                 System.out.println("IN SUCCESS BLOCK");

                             } else {

                                 ErrorMessageResponse error = ErrorUtils.parseError(response, retrofit);
                                 // … and use it to show error information
                                 if (error != null) {
                                     System.out.println("IN ERROR BLOCK");

                                     if (singletonUtil.showErrorMessage(error, framelayoutParent,
                                             getApplicationContext())) {
                                         callToUpdateNotificationStatus();
                                     }


                                 } else

                                     Toast.makeText(getApplicationContext(), "Unable to connect to server at this moment!! Please try again later!!", Toast.LENGTH_LONG);

                             }

                         }

                         @Override
                         public void onFailure(Call<ResponseMessage> call, Throwable t) {
                             // Log error here since request failed
                             Log.d(TAG, "onFailure: " + t.getMessage());
//                             progressBar.setVisibility(View.GONE);
//                             singletonUtil.showSnackBar(
//                                     "Unable to connect to server at this moment!! Please try again later!!",
//                                     relativeLayoutParent);

//                             {"access_token":"e86210f8-08ce-47d5-840a-408e47de56fc","token_type":"bearer","refresh_token":"12c8734b-3b20-439f-b177-49324d89e846","expires_in":1789,"scope":"read write"}


                         }
                     }

        );

    }*/


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
//https://www.saavn.com/s/album/english/Memories...Do-Not-Open-2017/jctRRkIqNww_



