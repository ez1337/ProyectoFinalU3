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

public class RVCarFragment extends Fragment {
    ArrayList<Car> cars;

    public RVCarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            Log.i("INFO","Datos OnCreate antes del filtro: "+this.cars);

            if(this.cars == null){
                if (getArguments() != null) {
                    this.cars = getArguments().getParcelableArrayList("cars-db");
                    Log.i("INFO","Datos en OnCreate: "+this.cars);
                }
            }
        }catch(NullPointerException npe){
            Log.i("INFO","Mensaje: "+npe.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_r_v_car,container,false);
        RecyclerView rvCars = view.findViewById(R.id.rv_cars);

        Log.i("INFO","Datos OnCreateView antes del filtro: "+this.cars);
        if(this.cars == null){
            if (getArguments() != null) {
                this.cars = getArguments().getParcelableArrayList("cars-db");
                Log.i("INFO","Datos en OnCreateView: "+this.cars);
            }
        }
        Log.i("INFO","Datos en OnCreateView2: "+this.cars);
        CarAdapter carAdapter = new CarAdapter(this.cars);
        rvCars.setAdapter(carAdapter);
        rvCars.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}