package com.example.asus.activiteas.DBPackage;

import com.example.asus.activiteas.Logic.ProductPeace;
import java.util.List;

public class ProductsHelper {
    private Product DataBase;

    public ProductsHelper(){
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
