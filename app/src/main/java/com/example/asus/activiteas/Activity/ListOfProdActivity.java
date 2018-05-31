package com.example.asus.activiteas.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.activiteas.Logic.FullListController;
import com.example.asus.activiteas.Logic.ListsUpdater;
import com.example.asus.activiteas.Logic.PDFHelper;
import com.example.asus.activiteas.Logic.StoragePermissions;
import com.example.asus.activiteas.Logic.VibrateService;
import com.example.asus.activiteas.R;
import com.example.asus.activiteas.Logic.FullList;
import com.example.asus.activiteas.Logic.ProductPeace;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ListOfProdActivity extends AppCompatActivity {


    protected FullListController fullListController;
    protected PDFHelper pdfHelper;
    protected VibrateService vibrator;
    protected ListsUpdater listsUpdater;
    protected StoragePermissions storagePermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_prod);

        fullListController = new FullListController();
        storagePermissions = new StoragePermissions();
        listsUpdater = new ListsUpdater();
        pdfHelper = new PDFHelper();
        Button buttonPdf = (Button) findViewById(R.id.createPdfList);
        Button buttonPrevious = (Button) findViewById(R.id.buttonObratno);
        fullListController.setFullList((FullList) getIntent().getParcelableExtra(FullList.class.getCanonicalName()));
        final List<ProductPeace> products = fullListController.fullListGetProducts();
        pdfHelper.setMode(fullListController.fullListGetLevel());
        pdfHelper.setNameOfFile(fullListController.fullListGetNameOfFile());
        pdfHelper.setNumOfPerson(fullListController.fullListGetNumOfPerson());
        Toast.makeText(getApplicationContext(),R.string.toast6,Toast.LENGTH_SHORT).show();
        final List<String> myList = new ArrayList<String>(listsUpdater.onList(products,
                fullListController.fullListGetLevel(),fullListController.fullListGetNumOfPerson()));

        final ListView listViewMain = (ListView) findViewById(R.id.lvMain1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        listViewMain.setAdapter(adapter);

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                fullListController.fullListRemove(position);
                myList.clear();
                myList.addAll(listsUpdater.onList(products, fullListController.fullListGetLevel(),fullListController.fullListGetNumOfPerson()));
                adapter.notifyDataSetInvalidated();
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfProdActivity.this, ListActivity.class);
                intent.putExtra(fullListController.fullListGet().getClass().getCanonicalName(),fullListController.fullListGet());
                finish();
                startActivity(intent);
            }
        });

        buttonPdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                try {
                    if(!fullListController.fullListGetProducts().isEmpty()){
                    pdfHelper.createPDF(fullListController.fullListGet(), storagePermissions.isStoragePermissionWriteGranted(getApplicationContext(),
                            ListOfProdActivity.this));
                    }else {
                        Toast.makeText(getApplicationContext(),R.string.toast9,Toast.LENGTH_SHORT).show();
                    }
                } catch (DocumentException | FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),R.string.toast7,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),R.string.toast7,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                vibrator= new VibrateService();
                vibrator.Vibrate(500, getApplicationContext());
                finish();
            }
        });
    }
}
