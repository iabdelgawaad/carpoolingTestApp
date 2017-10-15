package com.example.ibrahimabdelgawad.carpoolingtestapp.core;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ibrahimabdelgawad.carpoolingtestapp.R;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.fragment.CarFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, CarFragment.newInstance()).commit();
    }
}
