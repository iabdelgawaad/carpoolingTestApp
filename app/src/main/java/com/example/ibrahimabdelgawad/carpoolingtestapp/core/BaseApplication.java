package com.example.ibrahimabdelgawad.carpoolingtestapp.core;

import android.app.Application;

/**
 * Created by Ibrahim AbdelGawad on 10/14/2017.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationInstance();
    }

    private void initApplicationInstance() {
        instance = this;
    }
}
