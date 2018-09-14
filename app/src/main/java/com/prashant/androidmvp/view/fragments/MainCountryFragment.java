package com.prashant.androidmvp.view.fragments;
/**
 * @author : Prashant P
 * @Name: MainCountryFragment
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.prashant.androidmvp.R;
import com.prashant.androidmvp.models.Country;
import com.prashant.androidmvp.models.Row;
import com.prashant.androidmvp.presenters.CountryPresenter;
import com.prashant.androidmvp.utils.AppConstants;
import com.prashant.androidmvp.utils.AppController;
import com.prashant.androidmvp.utils.ListUtils;
import com.prashant.androidmvp.utils.Logger;
import com.prashant.androidmvp.utils.network.ConnectivityReceiver;
import com.prashant.androidmvp.view.adapters.CountryAdapter;
import com.prashant.androidmvp.view.listener.MainCountryView;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class MainCountryFragment extends Fragment implements MainCountryView {

    private static final String TAG = MainCountryFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private CountryPresenter mCountryPresenter;
    private CountryAdapter mCountryAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Toolbar mToolbar;
    private Button mBtnRetryError;
    private LinearLayout mLayoutRecycleView;
    private View mLayoutErrorView;
    private TextView mTxtMessageError;
    private Country mCountryList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myFragmentView = inflater.inflate(R.layout.fragment_main_country, container, false);
        setUpView(myFragmentView);
        return myFragmentView;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpFetchData();
    }

    private void setUpView(View mView) {
        mToolbar = (Toolbar) mView.findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mBtnRetryError = (Button) mView.findViewById(R.id.btnRetryError);
        mTxtMessageError = (TextView) mView.findViewById(R.id.txtMessageError);
        mLayoutRecycleView = (LinearLayout) mView.findViewById(R.id.layoutRecycleView);
        mLayoutErrorView = (View) mView.findViewById(R.id.layoutErrorView);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCountryPresenter = new CountryPresenter(getActivity(), this);
        setUpClickListeners();
    }

    private void setUpClickListeners() {

        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    setUpFetchData();
                }
            });
        }

        mBtnRetryError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpFetchData();
            }
        });
    }


    private void setUpFetchData() {
        if (mCountryPresenter != null) {
            boolean isConnected = ConnectivityReceiver.isConnected();
            if (isConnected) {
                mCountryPresenter.fetchData();
            }
            else
            {
                checkInternetConnectivity(isConnected);
            }
        }
    }


    @Override
    public void showProgress() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void hideProgress() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }


    @Override
    public void updateData(Country mCountryList) {
        clear();
        this.mCountryList = mCountryList;
        updateListView(mCountryList);
    }

    @Override
    public void displayErrorMessage(String mErrorMessage) {
        if (!TextUtils.isEmpty(mErrorMessage)) {
            mTxtMessageError.setText(mErrorMessage);
        }
    }

    private void updateListView(Country mCountryList) {
        if (mCountryList != null) {
            List<Row> mRowList = mCountryList.getRows();
            setTitleForActionBar(mCountryList.getTitle());
            if (mRowList != null) {
                try {
                    mRowList = ListUtils.removeEmptyList(mRowList);
                    mCountryAdapter = new CountryAdapter(getActivity(), mRowList);
                    mRecyclerView.setAdapter(mCountryAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            showErrorScreen();
            displayErrorMessage(getActivity().getResources().getString(R.string.no_data_found));
        }
    }

    private void setTitleForActionBar(String mTitle) {
        if (!TextUtils.isEmpty(mTitle)) {
            mToolbar.setTitle(mTitle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clear();
    }

    public void clear() {
        mRecyclerView.setAdapter(null);
    }

    @Override
    public void showErrorScreen() {
        mLayoutErrorView.setVisibility(View.VISIBLE);
        mLayoutRecycleView.setVisibility(View.GONE);
    }

    @Override
    public void hideErrorScreen() {
        mLayoutErrorView.setVisibility(View.GONE);
        mLayoutRecycleView.setVisibility(View.VISIBLE);
    }


    private void checkInternetConnectivity(boolean isConnected)
    {
        if (isConnected) {
            hideErrorScreen();
        } else {
            showErrorScreen();
            displayErrorMessage(getActivity().getResources().getString(R.string.not_connected_to_internet));

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Logger.d(TAG, " onSaveInstanceState");
        if (savedInstanceState != null)
        {
            savedInstanceState.putSerializable(AppConstants.COUNTRY_KEY, mCountryList);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Logger.d(TAG, " onViewStateRestored");
        if(savedInstanceState != null)
        {
            Country mCountryList = (Country) savedInstanceState.getSerializable(AppConstants.COUNTRY_KEY);
            updateListView(mCountryList);
        }
    }


}
