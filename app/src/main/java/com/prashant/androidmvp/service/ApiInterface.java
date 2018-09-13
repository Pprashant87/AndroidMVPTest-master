package com.prashant.androidmvp.service;


import com.prashant.androidmvp.models.Country;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiInterface {
    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<Country> getResult();
}
