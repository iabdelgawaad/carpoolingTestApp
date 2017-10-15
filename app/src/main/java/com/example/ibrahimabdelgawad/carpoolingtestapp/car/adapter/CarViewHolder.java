package com.example.ibrahimabdelgawad.carpoolingtestapp.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ibrahimabdelgawad.carpoolingtestapp.R;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.model.CarModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ibrahim AbdelGawad on 10/15/2017.
 */

public class CarViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.fuel)
    TextView fuel;

    CarViewHolder(View view, Context context) {
        super(view);
        ButterKnife.bind(this, view);
        this.context = context;
    }

    public void bind(final CarModel item, final CarAdapter.OnItemClickListener listener) {
        name.setText(item.getName());
        address.setText(item.getAddress());
        fuel.setText(item.getFuel() +"");

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClick(item);
            }
        });
    }
}
