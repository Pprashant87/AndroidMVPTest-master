package com.prashant.androidmvp.view.activity;
/**
 * @author : Prashant P
 * @Name: SplashScreenActivity
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.prashant.androidmvp.R;
import com.prashant.androidmvp.utils.AppController;
import com.prashant.androidmvp.utils.network.ConnectivityReceiver;
import com.prashant.androidmvp.view.fragments.MainCountryFragment;


public class SplashScreenActivity extends AppCompatActivity  {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        displaySplashScreen();
    }

    /*
     * Showing splash screen with a timer. This will be useful when you
     * want to show case your app logo / company
     */
    private void displaySplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
               finish();
            }
        }, SPLASH_TIME_OUT);
    }


}
