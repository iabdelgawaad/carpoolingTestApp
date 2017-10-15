package com.example.ibrahimabdelgawad.carpoolingtestapp.car.view;

import com.example.ibrahimabdelgawad.carpoolingtestapp.car.model.CarModel;
import com.example.ibrahimabdelgawad.carpoolingtestapp.core.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim AbdelGawad on 10/14/2017.
 */

public interface CarView extends BaseView {
    void showCarList(List<CarModel> carModelArrayList);

    void showEmptyView();

    void showLoading();

    void hideLoading();

    void showInlineError(String error);
}
