package com.example.dell.socialsift;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


/**
 * Created by MR JOSHI on 19-Jul-16.
 */
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //replace default fonts with custom fonts

    }

    //getter/setter for activity instance throught applciation
    private Activity mCurrentActivity = null;

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    /**
     * for enabling multidex
     *
     * @param base
     */
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

}
