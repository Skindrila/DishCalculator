package com.example.asus.activiteas.Logic;

import com.example.asus.activiteas.DataBase.Product;

import java.util.ArrayList;
import java.util.List;

public class ListsUpdater {
    public ListsUpdater(){
    }

    public List<String> onList(List<ProductPeace> products, double level, double numberOfPerson){
        List<String> myList = new ArrayList<String>();
        Calculator calculator = new Calculator();
        for (ProductPeace product : products){
            String data = "";
            data+=product.getName()+"\nПорция: ";
            Double i = calculator.OnList(numberOfPerson,level, product.getPortion());
            data+= Math.round(i);
            myList.add(data);
        }
        return myList;
    }

    public List<String> onList(List<Product> products, double level){
        List<String> myList = new ArrayList<String>();
        Calculator calculator = new Calculator();
        for (Product product : products){
            String data = "";
            data+=product.getName()+"\nПорция: ";
            Double i = calculator.OnList(level, product.getPortion());
            data+= Math.round(i);
            myList.add(data);
        }
        return myList;
    }

    public ArrayList<Long> dataSetInput(List<Product> products){
        ArrayList<Long> mDataSet = new ArrayList<Long>();
        for (int i = 0; i < products.size(); i++) {
            mDataSet.add(products.get(i).getId());
        }
        return mDataSet;
    }

    public List<String> forDelete(List<Product> products){
        List<String> tempList = new ArrayList<String>();
        for (Product product : products) {
            tempList.add(product.toString());
        }
        return tempList;
    }
}
