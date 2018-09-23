package com.prashant.androidmvp.Utils;
/**
 * @author : Prashant P
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 */
import android.view.View;

import com.prashant.androidmvp.utils.ViewUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ViewUtilsTest {

    @Mock
    View view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        Mockito.reset(view);
    }


    @Test
    public void testShowView() throws Exception {
        ViewUtils.showView(view);
        Mockito.verify(view).setVisibility(View.VISIBLE);
    }

    @Test
    public void testHideView() throws Exception {
        ViewUtils.hideView(view);
        Mockito.verify(view).setVisibility(View.GONE);
    }

    @Test
    public void testShowViewWithNullView() throws Exception {
        ViewUtils.showView(null);
        Mockito.verify(view, Mockito.never()).setVisibility(View.VISIBLE);
    }

    @Test
    public void testHideViewWithNullView() throws Exception {
        ViewUtils.hideView(null);
        Mockito.verify(view, Mockito.never()).setVisibility(View.GONE);
    }
}
