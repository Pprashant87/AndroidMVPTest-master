package com.prashant.androidmvp.utils;

import android.view.View;

public final class ViewUtils {

    public static void showView(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }
 
    public static void hideView(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }
}