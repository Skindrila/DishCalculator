package com.example.asus.activiteas.DataBase;

import com.example.asus.activiteas.Logic.ProductPeace;
import java.util.List;

public class DataBaseHelper {
    private Product DataBase;

    public DataBaseHelper(){
        DataBase = new Product();
    }

    public ProductPeace loadFromDataBase(Long id){
        DataBase = Product.findById(Product.class, id);
        ProductPeace product = new ProductPeace();
        product.setName(DataBase.getName());
        product.setPortion(DataBase.getPortion());
        return product;
    }

    public void saveToDataBase(ProductPeace products){
        DataBase = new Product(products.getName(), products.getPortion());
        DataBase.save();
    }

    public void deleteItem(Long id){
        DataBase = Product.findById(Product.class, id);
        DataBase.delete();
    }

    public List<Product> getAllList(){
        return Product.listAll(Product.class);
    }
}
