package com.example.asus.activiteas.Logic;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class FullList implements Parcelable{

    protected List<Products> products = new ArrayList<>();

    public FullList(){

    }

    public FullList(List<Products> products){

        this.products = products;
    }

    public void removeItem(int Index){
        products.remove(Index);
    }

    protected FullList(Parcel in) {
        products = in.createTypedArrayList(Products.CREATOR);
    }

    public void setProducts(List<Products> products){
        this.products = products;
    }

    public static final Creator<FullList> CREATOR = new Creator<FullList>() {
        @Override
        public FullList createFromParcel(Parcel in) {
            return new FullList(in);
        }

        @Override
        public FullList[] newArray(int size) {
            return new FullList[size];
        }
    };

    public List<Products> getProducts() {
        return products;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(products);
    }
}
