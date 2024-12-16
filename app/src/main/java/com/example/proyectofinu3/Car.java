package com.example.proyectofinu3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Car implements Parcelable {
    private String brand;
    private String model;
    private int year;
    private EngineType engine;
    private int image;
    private boolean favorite = false;

    protected Car(){}

    protected Car(Parcel in) {
        brand = in.readString();
        model = in.readString();
        year = in.readInt();
        image = in.readInt();
        favorite = in.readByte() != 0;
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int i) {
        dest.writeString(brand);
        dest.writeString(model);
        dest.writeInt(year);
        dest.writeInt(image);
        dest.writeByte((byte) (favorite ? 1 : 0));
    }

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
