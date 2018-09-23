package com.prashant.androidmvp.presenter;

import android.content.Context;

import com.prashant.androidmvp.helper.MockedResources;
import com.prashant.androidmvp.models.Country;
import com.prashant.androidmvp.presenters.CountryPresenter;
import com.prashant.androidmvp.view.listener.MainCountryView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class MainCountryPresenterTest implements MainCountryView {


    @Before
    public void setup() {

    }

    @Test
    public void testdisplayErrorMessage() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorScreen() {

    }

    @Override
    public void hideErrorScreen() {

    }

    @Override
    public void updateData(Country CountryList) {

    }

    @Override
    public void displayErrorMessage(String mErrorMessage) {

    }

    @After
    public void tearDown() {
       // Mockito.reset(mMainCountryView);
    }

}
