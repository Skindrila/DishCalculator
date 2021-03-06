package com.example.asus.activiteas.Activity;

import com.example.asus.activiteas.Logic.MyAdapter;
import com.example.asus.activiteas.Logic.PDFHelper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.asus.activiteas.Logic.StoragePermissions;
import com.example.asus.activiteas.R;
import java.io.File;

public class ViewPDFActivity extends AppCompatActivity {

    protected PDFHelper pdfHelper;
    protected MyAdapter myAdapter;
    protected StoragePermissions storagePermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        pdfHelper = new PDFHelper();
        storagePermissions = new StoragePermissions();
        File[] fileArray = pdfHelper.getRootDir();

        ListView filesListAdapter;

        filesListAdapter = (ListView) findViewById(R.id.lvMain);
        myAdapter = new MyAdapter(this, R.layout.list_item, fileArray,
                storagePermissions.isStoragePermissionReadGranted(getApplicationContext(),
                        this),this);
        filesListAdapter.setAdapter(myAdapter);
    }
}
