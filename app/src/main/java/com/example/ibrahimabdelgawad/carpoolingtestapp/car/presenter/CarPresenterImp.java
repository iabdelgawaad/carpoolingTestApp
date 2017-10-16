package com.example.ibrahimabdelgawad.carpoolingtestapp.car.presenter;

import android.util.Log;

import com.example.ibrahimabdelgawad.carpoolingtestapp.R;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.fragment.CarFragment;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.model.CarModel;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.model.Placemark;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.service.ApiClient;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.service.ApiInterface;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.view.CarView;
import com.example.ibrahimabdelgawad.carpoolingtestapp.core.BaseApplication;
import com.example.ibrahimabdelgawad.carpoolingtestapp.util.PrefUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ibrahimabdelgawad.carpoolingtestapp.car.fragment.CarFragment.CAR_LIST_KEY;

/**
 * Created by Ibrahim AbdelGawad on 10/15/2017.
 */

public class CarPresenterImp extends CarPresenter {
    CarView cartView;

    public CarPresenterImp(CarView carView) {
        this.cartView = carView;
    }

    public void populate() {
        cartView.showLoading();
        if (getDataFromShared() != null && getDataFromShared().size() > 0) {
            cartView.showCarList(getDataFromShared());
        } else {
            getCarList();
        }
    }

    @Override
    public void retry() {
        getCarList();
    }

    public void getCarList() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Placemark> call = apiService.getCarList();
        call.enqueue(new Callback<Placemark>() {

            @Override
            public void onResponse(Call<Placemark> call, Response<Placemark> response) {
                cartView.hideLoading();
                if (response.isSuccessful()) {
                    // use response data and do some fancy stuff :)
                    Placemark placemark = response.body();
                    cartView.showCarList(placemark.getPlacemarks());
                    saveDataToShared(placemark.getPlacemarks());
                } else {
                    Log.d("error message", response.message());
                    cartView.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Placemark> call, Throwable t) {
                cartView.hideLoading();
                if (t instanceof UnknownHostException) {
                    cartView.showError(BaseApplication.get().getString(R.string.no_connection_msg));
                } else
                    Log.d("error message", t.getMessage());
            }
        });
    }

    private void saveDataToShared(List<CarModel> carAdapterList) {
        //save to shared
        Gson gson = new Gson();
        String json = gson.toJson(carAdapterList);
        PrefUtils.saveToPrefs(BaseApplication.get(), CAR_LIST_KEY, json);
    }

    private List<CarModel> getDataFromShared() {
        Gson gson = new Gson();
        String json = PrefUtils.getFromPrefs(BaseApplication.get(), CarFragment.CAR_LIST_KEY, "");
        Type type = new TypeToken<ArrayList<CarModel>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
