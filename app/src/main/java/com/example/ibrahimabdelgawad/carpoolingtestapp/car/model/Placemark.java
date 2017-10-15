package com.example.ibrahimabdelgawad.carpoolingtestapp.car.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim AbdelGawad on 10/15/2017.
 */

public class Placemark {
    public List<CarModel> getPlacemarks() {
        return placemarks;
    }

    public void setPlacemarks(List<CarModel> placemarks) {
        this.placemarks = placemarks;
    }

    private List<CarModel> placemarks = new ArrayList<>();

}