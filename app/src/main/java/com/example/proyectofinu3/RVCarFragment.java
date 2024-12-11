package com.example.proyectofinu3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RVCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RVCarFragment extends Fragment {

    private static final String RV_KEY = "cars-db";

    private ArrayList<Car> cars;

    public RVCarFragment() {
        // Required empty public constructor
    }

    public static RVCarFragment newInstance(Bundle rvCarsBundle) {
        RVCarFragment fragment = new RVCarFragment();
        fragment.setArguments(rvCarsBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.cars == null){
            if (getArguments() != null) {
                this.cars = getArguments().getParcelableArrayList(RV_KEY);
                Log.i("INFO","Datos en OnCreate: "+this.cars);
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_r_v_car,container,false);
        RecyclerView rvCars = view.findViewById(R.id.rv_cars);

        if(this.cars == null){
            if (getArguments() != null) {
                this.cars = getArguments().getParcelableArrayList(RV_KEY);
                Log.i("INFO","Datos en OnCreateView: "+this.cars);
            }

        }

        Log.i("INFO","Datos en OnCreateView2: "+this.cars);
        CarAdapter carAdapter = new CarAdapter(cars);
        rvCars.setAdapter(carAdapter);
        rvCars.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}