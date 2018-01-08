package com.example.dell.socialsift.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.fragment.PeopleFragment;
import com.example.dell.socialsift.fragment.SiftFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 8/24/2017.
 */

public class NotificationActivity extends ParentActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_notification);
        init();
        setToolbar();
    }

    public void setToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mToolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
//        mToolbarTitle.setText("Edit");
        toolbar.setNavigationIcon(R.drawable.ic_nav);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setViewPager();


        System.out.println("IN ON CREATE");

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


        Fragment fragmentSift = new SiftFragment();
//
        adapter.addFragment(fragmentSift, "SIFT");

        Fragment fragmentFacebook = new PeopleFragment();

        adapter.addFragment(fragmentFacebook, "FACEBOOK");

        Fragment fragmentTwitter = new PeopleFragment();
//
        adapter.addFragment(fragmentTwitter, "TWITTER");

        Fragment fragmentInstra = new PeopleFragment();

        adapter.addFragment(fragmentInstra, "INSTRAGRAM");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
