package com.prashant.androidmvp.service;

/**
 * @author : Prashant P
 * Created in 2018 as an unpublished copyright work.
 * All rights reserved.
 */

import com.prashant.androidmvp.models.Country;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<Country> getResult();
}
