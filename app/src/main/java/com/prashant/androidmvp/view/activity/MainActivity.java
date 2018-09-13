package com.prashant.androidmvp.view.activity;

import android.graphics.Color;
import android.os.Bundle;
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
import com.prashant.androidmvp.utils.Logger;
import com.prashant.androidmvp.utils.network.ConnectivityReceiver;
import com.prashant.androidmvp.view.fragments.MainCountryFragment;

public class MainActivity extends AppCompatActivity   {

    private static final String TAG = MainActivity.class.getSimpleName();
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization(savedInstanceState);
    }

    private void initialization(Bundle savedInstanceState) {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        boolean addNewFragment = true;
        if(savedInstanceState!=null)
        {
            addNewFragment = false;
        }
        if(addNewFragment) {
            MainCountryFragment fragment = new MainCountryFragment();
            setFragment(fragment);
        }
        else
        {
            Logger.d(TAG,"Use exist fragment");
        }
    }


    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }


}
