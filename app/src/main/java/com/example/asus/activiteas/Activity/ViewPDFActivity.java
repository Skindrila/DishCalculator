package com.example.asus.activiteas.Activity;

import android.Manifest;

import com.example.asus.activiteas.Logic.MyAdapter;
import com.example.asus.activiteas.Logic.PDFHelper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.asus.activiteas.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewPDFActivity extends AppCompatActivity {

    private PDFHelper pdfHelper;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        final ArrayList<String> mDataSet = new ArrayList<String>();
        final List<String> myList = new ArrayList<String>();
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        pdfHelper = new PDFHelper();

        File rootFolder = Environment.getExternalStorageDirectory();
        rootFolder = new File(rootFolder + File.separator + "PdfFiles");
        if (!rootFolder.exists())
            rootFolder.mkdirs();
        File[] fileArray = rootFolder.listFiles();
        for(File file : fileArray){
            myList.add(file.getName());
        }

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = pdfHelper.viewFile(myList.get(position), isStoragePermissionGranted());
                try {
                    startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),R.string.toast8,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.adobe.reader&hl=ru")));
                }
            }
        });*/

        ListView filesListAdapter;

        filesListAdapter = (ListView) findViewById(R.id.lvMain);
        myAdapter = new MyAdapter(this, R.layout.list_item, fileArray,isStoragePermissionGranted());
        filesListAdapter.setAdapter(myAdapter);
    }


    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }

}
