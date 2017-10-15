package com.example.ibrahimabdelgawad.carpoolingtestapp.car.presenter;

import android.util.Log;

import com.example.ibrahimabdelgawad.carpoolingtestapp.R;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.model.Placemark;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.service.ApiClient;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.service.ApiInterface;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.view.CarView;
import com.example.ibrahimabdelgawad.carpoolingtestapp.core.BaseApplication;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        getCarList();
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
                }
                else
                    Log.d("error message", t.getMessage());
            }
        });
    }
}
