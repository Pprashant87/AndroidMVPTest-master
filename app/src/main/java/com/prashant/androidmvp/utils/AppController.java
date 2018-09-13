package com.prashant.androidmvp.utils;
/**
 * @author : Prashant P
 * @Name: AppController
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */

import android.app.Application;
import android.text.TextUtils;


import com.prashant.androidmvp.utils.network.ConnectivityReceiver;

public class AppController extends Application {

    private static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }


}
