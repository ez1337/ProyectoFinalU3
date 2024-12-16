package com.example.proyectofinu3;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Conjunto de datos

        ArrayList<Car> cars = new ArrayList<>(Arrays.asList(
                new Car("Alfa Romeo", "Giulia GTA", 1964, Car.EngineType.Inline4, R.drawable.alfa_giulia),
                new Car("Ferrari", "250 GTO", 1962, Car.EngineType.V12, R.drawable.ferrari_250gto),
                new Car("Lamborghini", "Miura", 1966, Car.EngineType.V12, R.drawable.lambo_miura),
                new Car("Ford", "Mustang Boss 429", 1969, Car.EngineType.V8, R.drawable.mustang_boss429),
                new Car("Porsche", "911 Carrera RS 2.7", 1973, Car.EngineType.Flat6, R.drawable.porsche_911_carrera_rs),
                new Car("Chevrolet", "Corvette Stingray", 1963, Car.EngineType.V8, R.drawable.corvette),
                new Car("Jaguar", "E-Type", 1961, Car.EngineType.Inline6, R.drawable.jag_etype),
                new Car("BMW", "2002 Turbo", 1973, Car.EngineType.Inline4, R.drawable.bmw_2002_turbo),
                new Car("Dodge", "Charger R/T", 1969, Car.EngineType.V8, R.drawable.charger_rt),
                new Car("Mercedes-Benz", "300 SL", 1954, Car.EngineType.Inline6, R.drawable.mercedes_300sl_gullwing),
                new Car("Aston Martin", "DB5", 1963, Car.EngineType.Inline6, R.drawable.db5_007),
                new Car("Toyota", "2000GT", 1967, Car.EngineType.Inline6, R.drawable.toyota_2000gt),
                new Car("Nissan", "Skyline GT-R (Hakosuka)", 1971, Car.EngineType.Inline6, R.drawable.skyline_kpgc10),
                new Car("Shelby", "Cobra 427", 1965, Car.EngineType.V8, R.drawable.cobra_427),
                new Car("Volkswagen", "Beetle", 1963, Car.EngineType.Flat4, R.drawable.vw_beetle),
                new Car("Citroën", "DS", 1955, Car.EngineType.Inline4, R.drawable.citroen_ds),
                new Car("Subaru", "360", 1958, Car.EngineType.Other, R.drawable.subaru_360),
                new Car("Honda", "S800", 1966, Car.EngineType.Inline4, R.drawable.honda_s800),
                new Car("Maserati", "Ghibli", 1967, Car.EngineType.V8, R.drawable.maserati_ghibli),
                new Car("Mazda", "Cosmo", 1967, Car.EngineType.Wankel, R.drawable.cosmo_110s),
                new Car("Lancia", "Stratos HF", 1973, Car.EngineType.V6, R.drawable.stratos)
        ));

        if(cars.isEmpty()){
            Log.e("ERROR","Array list de coches esta vacio o nulo");
        }
        /* Comprobación de existencia de un objeto Car nulo o de propiedades nulas en el objeto Car.
        * Inicialización del adaptador, RecyclerView y layout en el bloque finally*/
        int errorsFound = 0;
        try{
            for(int i = 0; i < cars.size(); i++){
            Car c = cars.get(i);
                if(c == null){
                    errorsFound++;
                    throw new NullPointerException(getResources().getString(R.string.NULL_ERROR) + i);
                }
                else if(c.getImage() == 0){
                    errorsFound++;
                    throw new Exception(getResources().getString(R.string.IMG_NOT_FOUND) + i);
                }
                else if(c.getBrand() == null || c.getModel() == null || c.getYear() == 0 || c.getEngine() == null){
                    errorsFound++;
                    throw new Exception(getResources().getString(R.string.NULL_PROPERTIES) + i);
                }
            }
        }catch(Exception e){
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }finally{
            try{
                if(errorsFound >= 1){
                    throw new Exception(getResources().getString(R.string.STARTUP_WARNING));
                }

                RVCarFragment fragment = new RVCarFragment();
                Bundle rvCarsBundle = new Bundle();

                rvCarsBundle.putParcelableArrayList("cars-db", cars);
                fragment.setArguments(rvCarsBundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentContainerView,fragment)
                        .addToBackStack(null)
                        .commit();
            }catch(Exception e){
                Log.w("WARNING", Objects.requireNonNull(e.getMessage()));
            }
        }

        /* Barra de navegación entre tabs
        * */
        BottomNavigationView navView = findViewById(R.id.bottomNavBar);
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.search_tab){
                    SearchFrgmnt searchFragment = new SearchFrgmnt();

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainerView, searchFragment,null)
                            .addToBackStack(null)
                            .commit();
                }
                else if(item.getItemId() == R.id.favlist_tab){
                    FavlistFrgmnt favFragment = new FavlistFrgmnt();

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainerView, favFragment,null)
                            .addToBackStack(null)
                            .commit();

                }
                else if(item.getItemId() == R.id.home_tab){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    RVCarFragment homeFragment = (RVCarFragment) fragmentManager.findFragmentById(R.id.rv_cars);

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainerView, Objects.requireNonNull(homeFragment),null)
                            .commit();
                }
                return true;
            }
        });
    }

}