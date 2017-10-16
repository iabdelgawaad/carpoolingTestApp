package com.example.ibrahimabdelgawad.carpoolingtestapp.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.example.ibrahimabdelgawad.carpoolingtestapp.R;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.fragment.CarFragment;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.model.CarModel;
import com.example.ibrahimabdelgawad.carpoolingtestapp.util.PrefUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<CarModel> carModelArrayList;
    private ArrayList<Marker> myMarkers;
    private LatLngBounds.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        carModelArrayList = new ArrayList<>();
        myMarkers = new ArrayList<Marker>();
        builder = new LatLngBounds.Builder();

        //get carList from shared
        Gson gson = new Gson();
        String json = PrefUtils.getFromPrefs(this, CarFragment.CAR_LIST_KEY, "");
        Type type = new TypeToken<ArrayList<CarModel>>() {
        }.getType();
        carModelArrayList = gson.fromJson(json, type);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng = null;
        for (int i = 0; i < carModelArrayList.size(); i++) {
            latLng = new LatLng(Double.parseDouble(carModelArrayList.get(i).getCoordinates().get(1) + ""),
                    Double.parseDouble(carModelArrayList.get(i).getCoordinates().get(0) + ""));

            Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(carModelArrayList.get(i).getName()));
            myMarkers.add(marker);
            builder.include(marker.getPosition());
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        //zoom to show all the markers
        animateCamera(builder);

        if (mMap != null) {
            //To show and Hide pins
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (myMarkers != null && myMarkers.size() > 0) {
                        for (Marker m : myMarkers) {
                            if (!m.equals(marker)) {
                                m.setVisible(m.isVisible() ? false : true);
                            }
                        }
                    }
                    return false;
                }
            });
        }
    }

    public void animateCamera(LatLngBounds.Builder builder) {
        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

        if (mMap != null)
            mMap.animateCamera(cu);
    }
}
