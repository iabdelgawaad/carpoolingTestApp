package com.example.ibrahimabdelgawad.carpoolingtestapp.car.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Ibrahim AbdelGawad on 10/14/2017.
 */

public class CarModel implements Parcelable {

    private String address;
    private List<Double> coordinates = null;
    private String engineType;
    private String exterior;
    private Integer fuel;
    private String interior;
    private String name;
    private String vin;

    public final static Parcelable.Creator<CarModel> CREATOR = new Creator<CarModel>() {
        public CarModel createFromParcel(Parcel in) {
            return new CarModel(in);
        }

        public CarModel[] newArray(int size) {
            return (new CarModel[size]);
        }

    };

    protected CarModel(Parcel in) {
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.coordinates, (java.lang.Double.class.getClassLoader()));
        this.engineType = ((String) in.readValue((String.class.getClassLoader())));
        this.exterior = ((String) in.readValue((String.class.getClassLoader())));
        this.fuel = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.interior = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.vin = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CarModel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public Integer getFuel() {
        return fuel;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(address);
        dest.writeList(coordinates);
        dest.writeValue(engineType);
        dest.writeValue(exterior);
        dest.writeValue(fuel);
        dest.writeValue(interior);
        dest.writeValue(name);
        dest.writeValue(vin);
    }

    public int describeContents() {
        return 0;
    }
}