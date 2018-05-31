package com.example.asus.activiteas.Logic;

import java.util.List;

public class FullListController {

    private FullList fullList;

    public FullListController(){
        fullList = new FullList();
    }

    public void setFullList(FullList fullList){
        this.fullList = fullList;
    }

    public void setFullListLevel(double level){
        fullList.setLevel(level);
    }

    public void setFullListNameOfFile(String nameOfFile){
        fullList.setNameOfFile(nameOfFile);
    }

    public void setFullListNumOfPeople(double numOfPeople){
        fullList.setNumPerson(numOfPeople);
    }

    public List<ProductPeace> fullListGetProducts(){
        return fullList.getProducts();
    }

    public double fullListGetLevel(){
        return fullList.getLevel();
    }

    public String fullListGetNameOfFile(){
        return fullList.getNameOfFile();
    }

    public double fullListGetNumOfPerson(){
        return fullList.getNumPerson();
    }

    public void fullListRemove(int position){
        fullList.removeItem(position);
    }

    public FullList fullListGet(){
        return fullList;
    }

    public void setFullListProducts(List<ProductPeace> list){
        fullList.setProducts(list);
    }
}
