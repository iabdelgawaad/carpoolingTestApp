package com.example.ibrahimabdelgawad.carpoolingtestapp.car.service;

import com.example.ibrahimabdelgawad.carpoolingtestapp.car.model.Placemark;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ibrahim AbdelGawad on 10/15/2017.
 */

public interface ApiInterface {
    @GET("locations")
    Call<Placemark> getCarList();
}
