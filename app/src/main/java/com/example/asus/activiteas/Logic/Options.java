package com.example.asus.activiteas.Logic;

public class Options {
    private double level;
    private int people;
    private String fileName;

    public Options(){
    }

    public void setLevel(double level){
        this.level=level;
    }

    public double getLevel(){
        return level;
    }

    public void setPeople(int people){
        this.people = people;
    }

    public int getPeople(){
        return people;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
