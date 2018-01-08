package com.example.dell.socialsift.sample;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;

import com.example.dell.socialsift.R;

public class MainActivity16 extends Activity {
	
	SharedPreferences pref;
	   
    private static String CONSUMER_KEY = "rmTjZBIXnkBn0KLKz211wjg55";
    private static String CONSUMER_SECRET = "LtDTZdHsUBTgu41ZBl7eiWAVrb2n70UhbS9X1qJi9dq9kPEPkr";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main16);
        pref = getPreferences(0);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("CONSUMER_KEY", CONSUMER_KEY);
        edit.putString("CONSUMER_SECRET", CONSUMER_SECRET);
        edit.commit();  

		Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();	              
        ft.replace(R.id.content_frame, login);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
	}


}
