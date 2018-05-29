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
import com.example.asus.activiteas.Logic.ListUpdater;
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

    protected FullList fullList;
    protected PDFHelper pdfHelper;
    protected VibrateService vibrator;
    protected ListUpdater listUpdater;
    protected StoragePermissions storagePermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_prod);

        storagePermissions = new StoragePermissions();
        listUpdater = new ListUpdater();
        pdfHelper = new PDFHelper();
        Button buttonPdf = (Button) findViewById(R.id.createPdfList);
        Button buttonPrevious = (Button) findViewById(R.id.buttonObratno);
        fullList = (FullList) getIntent().getParcelableExtra(FullList.class.getCanonicalName());
        final List<ProductPeace> products = fullList.getProducts();
        pdfHelper.setMode(fullList.getLevel());
        pdfHelper.setNameOfFile(fullList.getNameOfFile());
        pdfHelper.setNumOfPerson(fullList.getNumPerson());
        Toast.makeText(getApplicationContext(),R.string.toast6,Toast.LENGTH_SHORT).show();
        final List<String> myList = new ArrayList<String>(listUpdater.onList(products, fullList.getLevel(),fullList.getNumPerson()));

        ListView listViewMain = (ListView) findViewById(R.id.lvMain1);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        listViewMain.setAdapter(adapter);

        listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                fullList.removeItem(position);
                myList.clear();
                myList.addAll(listUpdater.onList(products, fullList.getLevel(),fullList.getNumPerson()));
                adapter.notifyDataSetInvalidated();
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfProdActivity.this, ListActivity.class);
                intent.putExtra(fullList.getClass().getCanonicalName(),fullList);
                finish();
                startActivity(intent);
            }
        });

        buttonPdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                try {
                    if(!fullList.getProducts().isEmpty()){
                    pdfHelper.createPDF(fullList, storagePermissions.isStoragePermissionWriteGranted(getApplicationContext(),
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
                Intent intent = new Intent(ListOfProdActivity.this,HomeActivity.class);
                vibrator= new VibrateService();
                vibrator.Vibrate(500, getApplicationContext());
                finish();
                startActivity(intent);
            }
        });
    }

    /*public  boolean isStoragePermissionGranted() {
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
    }*/
}
