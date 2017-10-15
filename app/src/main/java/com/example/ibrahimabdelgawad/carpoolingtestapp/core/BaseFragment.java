package com.example.ibrahimabdelgawad.carpoolingtestapp.core;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibrahimabdelgawad.carpoolingtestapp.R;

import butterknife.ButterKnife;

/**
 * Created by Ibrahim AbdelGawad on 10/14/2017.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    P presenter;

    public BaseFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_base_layout, container, false);
        ButterKnife.bind(this, baseView);

        return baseView;
    }

    public abstract P getPresenter();


    protected
    @NonNull
    P presenter() {
        if (presenter == null)
            presenter = getPresenter();
        return presenter;
    }

}
