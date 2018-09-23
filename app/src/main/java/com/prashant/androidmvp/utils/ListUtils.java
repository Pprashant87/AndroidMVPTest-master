package com.prashant.androidmvp.utils;
/**
 * @author : Prashant P
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 */

import com.prashant.androidmvp.models.Row;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    private static final String TAG = ListUtils.class.getSimpleName();

    /**
     * This method is used to remove empty row list
     * @param mRowList List<Row>
     * @return List<Row>
     */
    public static List<Row> removeEmptyList(List<Row> mRowList) {
        List<Row> mTempList = new ArrayList<>();
        if (mRowList == null || mRowList.isEmpty()) {
            return mTempList;
        } else {
            Row mRow = null;
            for (int i = 0; i < mRowList.size(); i++) {
                if (mRowList.get(i).getTitle() == null && mRowList.get(i).getDescription() == null && mRowList.get(i).getImageHref() == null) {
                    //Logger.d(TAG, "Remove position: " + String.valueOf(i));
                } else {
                    mRow = mRowList.get(i);
                    mTempList.add(mRow);
                }
            }

        }
        return mTempList;
    }
}
