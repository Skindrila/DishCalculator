package com.example.asus.activiteas.Logic;

import android.os.Parcel;
import android.os.Parcelable;

public class Products implements Parcelable{
    private String name;
    private int portion;

    public Products(){

    }

    public Products(String name, int portion){
        this.name = name;
        this.portion = portion;
    }

    protected Products(Parcel in) {
        name = in.readString();
        portion = in.readInt();
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flag){

        parcel.writeString(name);
        parcel.writeInt(portion);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPortion(int portion){
        this.portion = portion;
    }

    public int getPortion(){
        return portion;
    }

    public void plusPortion(Products product){
        this.portion+=product.getPortion();
    }

}
