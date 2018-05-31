package com.example.asus.activiteas.DataBase;

import com.orm.SugarRecord;

public class Product extends SugarRecord {
    String name;
    int portion;

    public Product(){
    }

    public Product(String name, int portion){
        this.name = name;
        this.portion = portion;
    }

    public String getName() {
        return name;
    }

    public int getPortion() {
        return portion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    @Override
    public String toString() {
        return name +"\n"+"Порция: "+ portion;
    }
}
