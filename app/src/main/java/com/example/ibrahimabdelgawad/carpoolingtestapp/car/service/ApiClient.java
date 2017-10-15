package com.example.ibrahimabdelgawad.carpoolingtestapp.car.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ibrahim AbdelGawad on 10/15/2017.
 */

public class ApiClient
{
    public static final String BASE_URL = "http://m-tribes.com/wp-content/uploads/coding-challenge/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
