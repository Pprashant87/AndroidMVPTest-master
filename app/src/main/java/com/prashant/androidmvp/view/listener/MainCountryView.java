package com.prashant.androidmvp.view.listener;

import com.prashant.androidmvp.models.Country;

/**
 * @author : Prashant P
 * @Name: MainCountryView
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 *
 */


public interface MainCountryView {

    void showProgress();

    void hideProgress();

    void showErrorScreen();

    void hideErrorScreen();

    void updateData(Country CountryList);

    void displayErrorMessage(String mErrorMessage);


}
