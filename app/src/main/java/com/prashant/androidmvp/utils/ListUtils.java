package com.prashant.androidmvp.utils;

import com.prashant.androidmvp.models.Row;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    private static final String TAG = ListUtils.class.getSimpleName();

    public static List<Row> removeEmptyList(List<Row> mRowList) {
        List<Row> mList = new ArrayList<>();
        if (mRowList.isEmpty()) {
            return mList;
        } else {
            for (int i = 0; i < mRowList.size(); i++) {
                if (mRowList.get(i).getTitle() == null && mRowList.get(i).getDescription() == null && mRowList.get(i).getImageHref() == null) {
                    mRowList.remove(i);
                }
            }
        }
        return mRowList;
    }
}
