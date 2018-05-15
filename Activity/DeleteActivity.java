package com.example.asus.activiteas.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.asus.activiteas.DBPackage.Product;
import com.example.asus.activiteas.R;
import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    public Product db = new Product();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        final List<Product> products = Product.listAll(Product.class);
        final ArrayList<Long> mDataSet = new ArrayList<Long>();
        final List<String> myList = new ArrayList<String>();
        for (int i = 0; i < products.size(); i++) {
            mDataSet.add(products.get(i).getId());
        }
        for (Product product : products){
            myList.add(product.toString());
        }
        final ListView lvMain = (ListView) findViewById(R.id.lvMain);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        lvMain.setAdapter(adapter);
        
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                db = Product.findById(Product.class, mDataSet.get(position));
                db.delete();
                Toast.makeText(getApplicationContext(),R.string.toast4,Toast.LENGTH_SHORT).show();
                List<Product> production = Product.listAll(Product.class);
                mDataSet.clear();
                for (int i = 0; i < production.size(); i++) {
                    mDataSet.add(production.get(i).getId());
                }
                List<String> newList = new ArrayList<String>();
                for (Product product : production){
                    newList.add(product.toString());
                }
                myList.clear();
                myList.addAll(newList);
                adapter.notifyDataSetInvalidated();
            }
        });
    }
}
