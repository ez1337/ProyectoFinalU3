package com.example.proyectofinu3;

public class Car {
    private String brand;
    private String model;
    private int year;
    private EngineType engine;
    private int image;

    public enum EngineType{
        V6,
        V8,
        V12,
        Inline4,
        Inline6,
        Other
    }

    public Car(String brand, String model, int year, EngineType engine, int image) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
