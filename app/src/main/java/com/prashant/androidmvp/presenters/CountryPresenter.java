package com.prashant.androidmvp.presenters;
/**
 * @author : Prashant P
 * @Name: CountryPresenter
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 */

import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.prashant.androidmvp.R;
import com.prashant.androidmvp.models.Country;
import com.prashant.androidmvp.service.ApiClient;
import com.prashant.androidmvp.utils.Logger;
import com.prashant.androidmvp.utils.network.ConnectivityReceiver;
import com.prashant.androidmvp.view.listener.MainCountryView;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryPresenter {

    private static final String TAG = CountryPresenter.class.getSimpleName();
    private Context mContext;
    private MainCountryView mMainCountryView;
    private ApiClient mApiClient;

    public CountryPresenter(Context mContext, MainCountryView mMainCountryView) {
        this.mContext = mContext;
        this.mMainCountryView = mMainCountryView;
        if (mApiClient == null) {
            mApiClient = new ApiClient();
        }
    }

    public void fetchData() {
        if (mApiClient != null) {
            getCountries();
        }
    }

    public void getCountries() {
        if (mMainCountryView != null) {
            mMainCountryView.hideErrorScreen();
            mMainCountryView.showProgress();
        }
        mApiClient.getClient().getResult()
                .enqueue(new Callback<Country>() {
                    @Override
                    public void onResponse(Call<Country> call, Response<Country> response) {
                        if (response != null && response.isSuccessful()) {
                            Logger.i(TAG, "Data was loaded from API.");
                            if (mMainCountryView != null) {
                                mMainCountryView.hideProgress();
                            }
                            try {
                                Country data = response.body();
                                if (mMainCountryView != null && data != null) {
                                    mMainCountryView.updateData(data);
                                }
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (mMainCountryView != null) {
                                mMainCountryView.showErrorScreen();
                                mMainCountryView.displayErrorMessage(mContext.getResources().getString(R.string.something_went_wrong));
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Country> call, Throwable t) {
                        Logger.e(TAG, "Unable to load the data from API.");
                        //Logger.e(TAG, t.getMessage());
                        String mErrorMessage = "";
                        if (!ConnectivityReceiver.isConnected()) {
                            mErrorMessage = mContext.getResources().getString(R.string.not_connected_to_internet);
                        } else {
                            mErrorMessage = mContext.getResources().getString(R.string.something_went_wrong);
                        }
                        if (mMainCountryView != null) {
                            mMainCountryView.hideProgress();
                            mMainCountryView.showErrorScreen();
                            mMainCountryView.displayErrorMessage(mErrorMessage);
                        }
                    }
                });
    }

    public void onDestroy() {
        mMainCountryView = null;
    }


}
