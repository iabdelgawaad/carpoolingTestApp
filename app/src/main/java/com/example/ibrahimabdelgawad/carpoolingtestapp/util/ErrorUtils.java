package com.example.ibrahimabdelgawad.carpoolingtestapp.util;

import com.google.gson.Gson;

import retrofit2.Response;

/**
 * Created by Ibrahim AbdelGawad on 10/11/2017.
 */

public class ErrorUtils {
    public static APIError parseError(Response<?> response) {
        APIError error = new Gson().fromJson(response.body().toString(), APIError.class);
        return error;
    }
}
