package com.example.asus.activiteas.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.asus.activiteas.DBPackage.Product;
import com.example.asus.activiteas.DBPackage.ProductsHelper;
import com.example.asus.activiteas.Logic.ListUpdater;
import com.example.asus.activiteas.R;
import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    protected ListUpdater listUpdater;
    protected ProductsHelper productsHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        listUpdater = new ListUpdater();
        productsHelper = new ProductsHelper();

        final List<Product> products = Product.listAll(Product.class);
        final ArrayList<Long> mDataSet = new ArrayList<Long>(listUpdater.dataSetInput(products));
        final List<String> myList = new ArrayList<String>(listUpdater.forDelete(products));
        final ListView lvMain = (ListView) findViewById(R.id.lvMain);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                productsHelper.deleteItem(mDataSet.get(position));
                products.clear();
                products.addAll(productsHelper.getAllList());
                mDataSet.clear();
                mDataSet.addAll(listUpdater.dataSetInput(products));
                myList.clear();
                myList.addAll(listUpdater.forDelete(products));
                adapter.notifyDataSetInvalidated();
                Toast.makeText(getApplicationContext(), R.string.toast4, Toast.LENGTH_SHORT).show();
            }
        });
    }
}