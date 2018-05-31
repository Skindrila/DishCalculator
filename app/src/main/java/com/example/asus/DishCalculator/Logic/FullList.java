package com.example.asus.activiteas.Logic;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class FullList implements Parcelable{

    private List<ProductPeace> products;
    private double level;
    private double numPerson;
    private String nameOfFile;

    public FullList(){
    }

    public FullList(List<ProductPeace> products){

        this.products = products;
    }

    public void removeItem(int Index){
        products.remove(Index);
    }

    protected FullList(Parcel in) {
        products = in.createTypedArrayList(ProductPeace.CREATOR);
        level = in.readDouble();
        nameOfFile = in.readString();
        numPerson = in.readDouble();
    }

    public void setProducts(List<ProductPeace> products){
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

    public List<ProductPeace> getProducts() {
        return products;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public void setNumPerson(double numPerson) {
        this.numPerson = numPerson;
    }

    public double getLevel() {
        return level;
    }

    public double getNumPerson() {
        return numPerson;
    }

    public String getNameOfFile() {
        return nameOfFile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(products);
        dest.writeDouble(level);
        dest.writeString(nameOfFile);
        dest.writeDouble(numPerson);
    }
}
