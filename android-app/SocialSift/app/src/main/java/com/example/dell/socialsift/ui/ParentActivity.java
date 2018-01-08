package com.example.dell.socialsift.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dell.socialsift.CustomApplication;
import com.example.dell.socialsift.R;

/**
 * Created by Replete Android on 9/8/2016.
 */
public class ParentActivity extends AppCompatActivity implements View.OnClickListener {

    protected CustomApplication mCustomApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomApplication = (CustomApplication) this.getApplicationContext();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //set currentActivity instance of application
        mCustomApplication.setCurrentActivity(this);
    }

    protected void onPause() {
        clearReferences();
        super.onPause();
    }

    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    /**
     * clear current activity instance on activity destroy
     */
    private void clearReferences() {
        Activity currActivity = mCustomApplication.getCurrentActivity();
        if (this.equals(currActivity))
            mCustomApplication.setCurrentActivity(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            //call onBackPressed() on pressing home button(so that it works same as hardware back button)
            case android.R.id.home:
                onBackPressed();
                break;



        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

}
