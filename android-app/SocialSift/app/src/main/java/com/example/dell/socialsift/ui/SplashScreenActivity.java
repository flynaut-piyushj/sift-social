package com.example.dell.socialsift.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.socialsift.R;
import com.example.dell.socialsift.ui.loginUI.LoginActivity;
import com.example.dell.socialsift.utils.commonUtils.SessionManager;

/**
 * Created by DELL on 8/22/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        sessionManager = new SessionManager(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.getLogin(getApplicationContext())) {

                    Intent main_intent = new Intent(getApplicationContext(), DashBoardActivity.class);
                    main_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(main_intent);
                    finish();
                } else {
                    Intent main_intent = new Intent(getApplicationContext(), LoginActivity.class);
                    main_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(main_intent);
                    finish();
                }

            }
        }, 2000);

    }

}
