package com.example.ibrahimabdelgawad.carpoolingtestapp.car.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibrahimabdelgawad.carpoolingtestapp.R;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.adapter.CarAdapter;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.model.CarModel;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.presenter.CarPresenter;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.presenter.CarPresenterImp;
import com.example.ibrahimabdelgawad.carpoolingtestapp.car.view.CarView;
import com.example.ibrahimabdelgawad.carpoolingtestapp.core.BaseApplication;
import com.example.ibrahimabdelgawad.carpoolingtestapp.core.BaseFragment;
import com.example.ibrahimabdelgawad.carpoolingtestapp.map.MapsActivity;
import com.example.ibrahimabdelgawad.carpoolingtestapp.util.PrefUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarFragment extends BaseFragment<CarPresenter> implements CarView, CarAdapter.OnItemClickListener {

    private OnFragmentInteractionListener mListener;
    private ProgressDialog pd;
    private RecyclerView recyclerView;
    private CarAdapter adapter;
    private List<CarModel> carAdapterList = new ArrayList<>();
    public static final String CAR_LIST_KEY = "car_list_key";


    public CarFragment() {
        // Required empty public constructor
    }

    public static CarFragment newInstance() {
        CarFragment fragment = new CarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            pd = new ProgressDialog(getActivity());
            pd.setMessage(BaseApplication.get().getString(R.string.loading_text));
            pd.setCancelable(false);

            getPresenter().populate();
        }
        return view;
    }

    @Override
    public CarPresenter getPresenter() {
        return new CarPresenterImp(this);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showCarList(List<CarModel> carModelArrayList) {
        this.carAdapterList = carModelArrayList;
        adapter = new CarAdapter(getActivity(), carAdapterList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //save to shared
        Gson gson = new Gson();
        String json = gson.toJson(this.carAdapterList);
        PrefUtils.saveToPrefs(getActivity() , CAR_LIST_KEY , json);
    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void showInlineError(String error) {

    }

    @Override
    public void showError(String error) {
        showRetryDialog(error);
    }

    @Override
    public void onItemClick(CarModel item) {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        getActivity().startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void showLoadingDialog() {
        if (pd != null && pd.isShowing()) {
            pd.show();
        }
    }

    public void hideLoadingDialog() {
        if (pd != null) {
            pd.hide();
        }
    }

    public void showRetryDialog(String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                R.string.retry_text,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        getPresenter().retry();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
