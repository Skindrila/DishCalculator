package com.example.asus.activiteas;

import com.example.asus.DishCalculator.DataBase.Product;
import com.example.asus.DishCalculator.Logic.Calculator;
import com.example.asus.DishCalculator.Logic.FullListController;
import com.example.asus.DishCalculator.Logic.ListsUpdater;
import com.example.asus.DishCalculator.Logic.ProductPeace;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class UnitTest {
    private Calculator calculator = new Calculator();
    private String Test_Text = "Test";
    private Double data1 = 2.0;
    private Double data2 = 1.5;
    private int data3 = 5;
    private java.util.List<ProductPeace> products;
    private ProductPeace productPeace;
    private java.util.List<String> myList;
    private FullListController fullListController;
    private Product product;

    @Test
    public void calculator_test() {
        Double expected1 = 15.0;
        Double expected2 = 10.0;
        assertEquals("Checking that first option is work",calculator.OnList(data1,data2,data3),expected1);
        assertEquals("Checking that second option is work",calculator.OnList(data1,data3),expected2);
    }

    @Test
    public void listsUpdater_test(){
        myList = new ArrayList<String>();
        productPeace = new ProductPeace();
        productPeace.setName(Test_Text);
        productPeace.setPortion(data3);
        products = new ArrayList<ProductPeace>();
        products.add(productPeace);
        ListsUpdater listsUpdater = new ListsUpdater();
        Double i = calculator.OnList(data1,data2, productPeace.getPortion());
        String str = Test_Text+"\nПорция: ";
        str+= Math.round(i);
        myList.add(str);
        assertEquals("Checking that list updater working correct",listsUpdater.onList(products,data2,data1),myList);
    }


    @Test
    public void fullListController_tests(){
        fullListController = new FullListController();
        productPeace = new ProductPeace();

        fullListController.setFullListNumOfPeople(data1);
        fullListController.setFullListLevel(data2);
        fullListController.setFullListNameOfFile(Test_Text);

        productPeace.setName(Test_Text);
        productPeace.setPortion(data3);
        products = new ArrayList<ProductPeace>();
        products.add(productPeace);

        fullListController.setFullListProducts(products);

        assertEquals("Checking that fullListController working correct",fullListController.fullListGetNameOfFile(),Test_Text);
        assertEquals("Checking that fullListController working correct",fullListController.fullListGetLevel(),data2,5);
        assertEquals("Checking that fullListController working correct",fullListController.fullListGetNumOfPerson(),data1,0);
        assertEquals("Checking that fullListController working correct",fullListController.fullListGetProducts(),products);
    }

    @Test
    public void product_tests(){
        product = new Product();
        product.setName(Test_Text);
        product.setPortion(data3);
        String str = Test_Text+"\nПорция: "+data3;

        assertEquals("Checking that product working correct",product.getName(),Test_Text);
        assertEquals("Checking that product working correct",product.getPortion(),data3);
        assertEquals("Checking that product working correct",product.toString(),str);
    }
}