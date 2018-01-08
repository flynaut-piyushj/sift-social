package com.example.dell.socialsift.ui;


import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.dell.socialsift.R;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by DELL on 8/23/2017.
 */

public class
SampleACtivity extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}
