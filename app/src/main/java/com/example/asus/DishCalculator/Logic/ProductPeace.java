package com.example.asus.activiteas.Logic;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductPeace implements Parcelable{
    private String name;
    private int portion;

    public ProductPeace(){

    }

    public ProductPeace(String name, int portion){
        this.name = name;
        this.portion = portion;
    }

    protected ProductPeace(Parcel in) {
        name = in.readString();
        portion = in.readInt();
    }

    public static final Creator<ProductPeace> CREATOR = new Creator<ProductPeace>() {
        @Override
        public ProductPeace createFromParcel(Parcel in) {
            return new ProductPeace(in);
        }

        @Override
        public ProductPeace[] newArray(int size) {
            return new ProductPeace[size];
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

    public void plusPortion(ProductPeace product){
        this.portion+=product.getPortion();
    }
}
