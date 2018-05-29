package com.example.asus.activiteas.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import com.example.asus.activiteas.DataBase.Product;
import com.example.asus.activiteas.DataBase.ProductsHelper;
import com.example.asus.activiteas.Logic.ListUpdater;
import com.example.asus.activiteas.Logic.PreferencesController;
import com.example.asus.activiteas.Logic.ProductPeace;
import com.example.asus.activiteas.R;
import com.example.asus.activiteas.Logic.FullList;


public class ListActivity extends Activity {

    protected List<ProductPeace> listOfProducts = new ArrayList<ProductPeace>();
    protected FullList fullList;
    protected Button toListOfProducts;
    protected ListUpdater listUpdater;
    protected ProductsHelper productsHelper;
    protected PreferencesController preferencesController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toast.makeText(getApplicationContext(),R.string.toast5,Toast.LENGTH_SHORT).show();

        toListOfProducts =(Button) findViewById(R.id.toListOfProdButton);

        preferencesController = new PreferencesController();
        productsHelper = new ProductsHelper();
        listUpdater = new ListUpdater();
        fullList = new FullList();
        fullList.setLevel(Double.parseDouble(
                preferencesController.load("mode",getApplicationContext())));
        fullList.setNameOfFile(preferencesController.load(
                "file_name",getApplicationContext()));
        fullList.setNumPerson(Double.parseDouble(
                preferencesController.load("num",getApplicationContext())));

        FullList intentList;
        intentList = (FullList) getIntent().getParcelableExtra(FullList.class.getCanonicalName());
        if(intentList != null)
        listOfProducts = intentList.getProducts();

        final List<Product> products = Product.listAll(Product.class);
        final ArrayList<Long> mDataSet = new ArrayList<Long>(listUpdater.dataSetInput(products));
        List<String> myList = new ArrayList<String>(listUpdater.onList(products,fullList.getLevel()));

        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ProductPeace product = productsHelper.loadFromDataBase(mDataSet.get(position));
                listOfProducts.add(product);
                fullList.setProducts(listOfProducts);
            }
        });

        toListOfProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullList.setProducts(listOfProducts);
                Intent intent = new Intent(ListActivity.this, ListOfProdActivity.class);
                intent.putExtra(fullList.getClass().getCanonicalName(),fullList);
                finish();
                startActivity(intent);
            }
        });
    }
}