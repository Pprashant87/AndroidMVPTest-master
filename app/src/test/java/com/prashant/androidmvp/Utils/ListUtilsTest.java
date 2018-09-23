package com.prashant.androidmvp.Utils;
/**
 * @author : Prashant P
 * @Name: MainCountryFragment
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 */
import android.view.View;

import com.prashant.androidmvp.models.Row;
import com.prashant.androidmvp.utils.ListUtils;
import com.prashant.androidmvp.utils.ViewUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ListUtilsTest {

    private static final int MAX_SIZE = 10;
    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Description";
    private static final String IMAGEHREF = "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg";

    @Before
    public void setup() {
    }


    @Test
    public void testEmptyList() throws Exception {
        List<Row> mMainList = new ArrayList<>();
        List<Row> mTempList = ListUtils.removeEmptyList(mMainList);
        Assert.assertEquals(mTempList, mMainList);
    }

    @Test
    public void testNullList() throws Exception {
        List<Row> mMainList = new ArrayList<>();
        List<Row> mtempList = ListUtils.removeEmptyList(null);
        Assert.assertEquals(mtempList, mMainList);
        //Assert.assertNotNull(mtempList);
    }

    @Test
    public void testNotNullList() throws Exception {
        List<Row> mtempList = ListUtils.removeEmptyList(null);
        Assert.assertNotNull(mtempList);
    }

    @Test
    public void testSizeOfList() throws Exception {
        List<Row> mMainList = new ArrayList<>();
        Row mRow = null;
        for (int i = 0; i < MAX_SIZE; i++) {
            mRow = new Row();
            mRow.setTitle(TITLE);
            mRow.setDescription(DESCRIPTION);
            mRow.setImageHref(IMAGEHREF);
            mMainList.add(mRow);
        }
        List<Row> mtempList = ListUtils.removeEmptyList(mMainList);
        Assert.assertEquals(mMainList.size(), mtempList.size());
    }

    @Test
    public void testNullArrayList() throws Exception {
        List<Row> mMainList = new ArrayList<>();
        Row mRow = null;
        for (int i = 0; i < MAX_SIZE; i++) {
            mRow = new Row();
            mRow.setTitle(null);
            mRow.setDescription(null);
            mRow.setImageHref(null);
            mMainList.add(mRow);
        }
        List<Row> mtempList = ListUtils.removeEmptyList(mMainList);
        Assert.assertEquals(mtempList.size(), 0);
    }

    @Test
    public void testDataAndNullArrayList() throws Exception {
        List<Row> mMainList = new ArrayList<>();
        Row mRow = null;
        for (int i = 0; i < MAX_SIZE; i++) {
            mRow = new Row();
            if (i % 2 == 0) {
                mRow.setTitle(null);
                mRow.setDescription(null);
                mRow.setImageHref(null);
            } else {
                mRow.setTitle(TITLE);
                mRow.setDescription(DESCRIPTION);
                mRow.setImageHref(IMAGEHREF);
            }
            mMainList.add(mRow);
        }
        List<Row> mtempList = ListUtils.removeEmptyList(mMainList);
        Assert.assertEquals(mtempList.size(), 5);
    }

}
