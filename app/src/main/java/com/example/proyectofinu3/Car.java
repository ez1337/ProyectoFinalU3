package com.example.proyectofinu3;

public class Car {
    private String brand;
    private String model;
    private int year;
    private EngineType engine;
    private int image;
    private boolean favorite = false;

    public enum EngineType{
        V6,
        V8,
        V12,
        Inline4,
        Inline6,
        Flat4,
        Flat6,
        Wankel,
        Other
    }

    public Car(String brand, String model, int year, EngineType engine, int image) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.image = image;
    }

    public void setFavorite(){this.favorite = !favorite;}

    public boolean getFavorite(){return favorite;}

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public EngineType getEngine() {
        return engine;
    }

    public int getImage() {
        return image;
    }


    @Override
    public String toString() {
        return "Car: "+brand+" "+model+" / Year: "+year;
    }
}
