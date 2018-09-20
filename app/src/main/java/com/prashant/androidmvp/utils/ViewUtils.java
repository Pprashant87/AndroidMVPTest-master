package com.prashant.androidmvp.utils;
/**
 * @author : Prashant P
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 */
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