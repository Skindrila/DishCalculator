package com.example.asus.activiteas.Logic;

public class Calculator {
    public Calculator(){

    }

    public Double OnList(Double numOfPerson, Double level, int portion){
        return numOfPerson*level*portion;
    }

    public Double OnList(Double numOfPerson, int portion){
        return numOfPerson*portion;
    }
}
