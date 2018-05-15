package com.example.asus.activiteas.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.asus.activiteas.DBPackage.Product;
import com.example.asus.activiteas.R;
import com.example.asus.activiteas.logic.FullList;
import com.example.asus.activiteas.logic.Products;


public class ListActivity extends Activity {

    protected Product db = new Product();
    protected List<Products> listOfProducts = new ArrayList<Products>();
    protected FullList fullList = new FullList();
    protected Button button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toast.makeText(getApplicationContext(),R.string.toast5,Toast.LENGTH_SHORT).show();

        FullList intentList = new FullList();
        intentList = (FullList) getIntent().getParcelableExtra(FullList.class.getCanonicalName());
        if(intentList != null)
        listOfProducts = intentList.getProducts();


        final List<Product> products = Product.listAll(Product.class);
        List<String> myList = new ArrayList<String>();
        final ArrayList<Long> mDataSet = new ArrayList<Long>();
        button =(Button) findViewById(R.id.toListOfProdButton);

        for (int i = 0; i < products.size(); i++) {
            mDataSet.add(products.get(i).getId());
        }

        for (Product product : products){
            String data = "";
            data+=product.getName()+"\nПорция: ";
            Double i = getModeLevel()*product.getPortion();
            data+= Math.round(i);
            myList.add(data);
        }

        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        lvMain.setAdapter(adapter);


        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                db = Product.findById(Product.class, mDataSet.get(position));
                Products pro = new Products();
                pro.setName(db.getName());
                pro.setPortion(db.getPortion());
                listOfProducts.add(pro);
                fullList.setProducts(listOfProducts);
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullList.setProducts(listOfProducts);
                Intent intent = new Intent(ListActivity.this, ListOfProdActivity.class);
                intent.putExtra(fullList.getClass().getCanonicalName(),fullList);
                startActivity(intent);
            }
        });
    }

    public double getModeLevel(){
        final String SAVED_TEXT = "mode";
        SharedPreferences sPref;
        sPref = getSharedPreferences("Options",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        return Double.parseDouble(savedText);
    }
}