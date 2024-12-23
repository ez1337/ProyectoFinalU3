package com.example.proyectofinu3;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>{


    // Contenedor de datos
    private ArrayList<Car> carList;

    // Constructor de CarAdapter. Solo recibe la lista de coches desde MainActivity.
    public CarAdapter(ArrayList<Car> carList){

        this.carList = carList;
    }


    // Este método crea los ViewHolder de cada coche
    @NonNull
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CarAdapter.CarViewHolder carViewHolder =
                new CarViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item,parent,false)
                );
        return carViewHolder;
    }


    // Aquí se bindea la interfaz visual hecha en el ViewHolder con los elementos definidos en el xml
    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        StringBuilder model = new StringBuilder(car.getBrand()+" "+car.getModel());
        holder.img.setImageResource(car.getImage());
        holder.tv_model.setText(model);
        holder.tv_year.setText(String.valueOf(car.getYear()));
        holder.tv_engine.setText(car.getEngine().toString());
        holder.save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                car.setFavorite();
                if(isChecked){
                    Log.i("INFO","Car: "+car.getBrand()+" "+car.getModel()+" saved!");
                    showFavList(carList);
                }
                else{
                    Log.i("INFO","Car: "+car.getBrand()+" "+car.getModel()+" unsaved!");
                    showFavList(carList);
                }
            }

            public void showFavList(ArrayList<Car> carList){
                for(Car c : carList){
                    if(c.getFavorite()){
                        System.out.println(c.toString());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    // Sub-clase ViewHolder. Aquí se define la interfaz visual del item a añadir al RecyclerView
    public class CarViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv_model;
        TextView tv_year;
        TextView tv_engine;
        Switch save;


        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.carPicture);
            tv_model = itemView.findViewById(R.id.tv_model);
            tv_year = itemView.findViewById(R.id.tv_year);
            tv_engine = itemView.findViewById(R.id.tv_engine);
            save = itemView.findViewById(R.id.fav_switch);

        }
    }
}
