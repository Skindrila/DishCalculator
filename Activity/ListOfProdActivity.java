package com.example.asus.activiteas.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.activiteas.DBPackage.Product;
import com.example.asus.activiteas.R;
import com.example.asus.activiteas.logic.FullList;
import com.example.asus.activiteas.logic.Products;

import java.util.ArrayList;
import java.util.List;

public class ListOfProdActivity extends AppCompatActivity {

    protected FullList fullList;
    private Button buttonPdf;
    private Button buttonPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_prod);

        Toast.makeText(getApplicationContext(),R.string.toast6,Toast.LENGTH_SHORT).show();

        buttonPdf = (Button) findViewById(R.id.createPdfList);
        buttonPrev = (Button) findViewById(R.id.buttonObratno);

        final List<String>myList = new ArrayList<String>();
        fullList = (FullList) getIntent().getParcelableExtra(FullList.class.getCanonicalName());

        final List<Products> products= fullList.getProducts();

        for (Products product : products){
            String data = "";
            data+=product.getName()+"\nПорция: ";
            Double i = getModeLevel()*product.getPortion()*getNumberOfPerson();
            data+= Math.round(i);
            myList.add(data);
        }

        ListView lvMain = (ListView) findViewById(R.id.lvMain1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        lvMain.setAdapter(adapter);


        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                List<String> newList = new ArrayList<String>();
                fullList.removeItem(position);
                for (Products product : products){
                    String data = "";
                    data+=product.getName()+"\nПорция: ";
                    Double i = getModeLevel()*product.getPortion()*getNumberOfPerson();
                    data+= Math.round(i);
                    newList.add(data);
                }
                myList.clear();
                myList.addAll(newList);
                adapter.notifyDataSetInvalidated();
            }
        });


        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfProdActivity.this, ListActivity.class);
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

    public Double getNumberOfPerson(){
        final String SAVED_TEXT = "num";
        SharedPreferences sPref;
        sPref = getSharedPreferences("Options",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        return Double.parseDouble(savedText);
    }

}
