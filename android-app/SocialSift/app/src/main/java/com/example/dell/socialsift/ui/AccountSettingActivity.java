package com.example.dell.socialsift.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.ui.loginUI.LoginActivity;
import com.example.dell.socialsift.utils.commonUtils.SessionManager;

/**
 * Created by DELL on 8/23/2017.
 */

public class AccountSettingActivity extends ParentActivity {
    private TextView txt_edit_profile;
    private Toolbar toolbar;
private TextView txt_notification;
    private TextView txt_login;
    private TextView txt_logout;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        init();
        setToolbar();

    }

    public void init(){
        txt_logout = (TextView) findViewById(R.id.txt_logout);
        txt_logout.setOnClickListener(this);
        sessionManager = new SessionManager(getApplicationContext());
        txt_notification = (TextView) findViewById(R.id.txt_notification);
        txt_notification.setOnClickListener(this);
        txt_login = (TextView) findViewById(R.id.txt_login);
        txt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_login:
                Intent i = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(i);


                break;

            case R.id.txt_notification:
                Intent register_intent = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(register_intent);
                break;

            case R.id.txt_logout:
                sessionManager.setLogin(true);
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent);
                finish();
                break;

        }
    }



    public void setToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mToolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mToolbarTitle.setText("Account Setting");
        toolbar.setNavigationIcon(R.drawable.ic_back2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
}