package com.example.asus.activiteas.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.asus.activiteas.Logic.PDFHelper;
import com.example.asus.activiteas.R;
import com.example.asus.activiteas.Logic.FullList;
import com.example.asus.activiteas.Logic.Products;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ListOfProdActivity extends AppCompatActivity {

    protected FullList fullList;
    private Button buttonPdf;
    private Button buttonPrev;
    private PDFHelper pdfHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_prod);

        Toast.makeText(getApplicationContext(),R.string.toast6,Toast.LENGTH_SHORT).show();

        pdfHelper = new PDFHelper();
        buttonPdf = (Button) findViewById(R.id.createPdfList);
        buttonPrev = (Button) findViewById(R.id.buttonObratno);
        fullList = (FullList) getIntent().getParcelableExtra(FullList.class.getCanonicalName());
        final List<Products> products= fullList.getProducts();
        final List<String>myList = new ArrayList<String>();

        pdfHelper.setMode(loadLevel());
        pdfHelper.setNameOfFile(loadNameOfFile());
        pdfHelper.setNumOfPerson(loadNumOfPerson());

        for (Products product : products){
            String data = "";
            data+=product.getName()+"\nПорция: ";
            Double i = loadLevel()*product.getPortion()*loadNumOfPerson();
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
                    Double i = loadLevel()*product.getPortion()*loadNumOfPerson();
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

        buttonPdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                try {
                    pdfHelper.createPDF(fullList, isStoragePermissionGranted());
                } catch (DocumentException | FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),R.string.toast7,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                Intent intent = new Intent(ListOfProdActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public Double loadNumOfPerson(){
        final String SAVED_TEXT = "num";
        SharedPreferences sPref;
        sPref = getSharedPreferences("Options",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        return Double.parseDouble(savedText);
    }

    public String loadNameOfFile(){
        final String SAVED_TEXT = "file_name";
        SharedPreferences sPref;
        sPref = getSharedPreferences("Options",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        return savedText;
    }

    public Double loadLevel(){
        final String SAVED_TEXT = "mode";
        SharedPreferences sPref;
        sPref = getSharedPreferences("Options",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        return Double.parseDouble(savedText);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
}
