package com.example.ibrahimabdelgawad.carpoolingtestapp.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibrahimabdelgawad.carpoolingtestapp.R;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.model.CarModel;

import java.util.List;

/**
 * Created by Ibrahim AbdelGawad on 10/15/2017.
 */

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    private Context mContext;
    private List<CarModel> carModelList;

    public interface OnItemClickListener {
        void onItemClick(CarModel item);
    }

    private final OnItemClickListener listener;

    public CarAdapter(Context mContext, List<CarModel> carModelList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.carModelList = carModelList;
        this.listener = listener;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_list_item, parent, false);
        return new CarViewHolder(itemView, mContext);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        holder.bind(carModelList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return carModelList.size();
    }
}