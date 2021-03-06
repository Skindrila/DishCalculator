package com.example.asus.activiteas.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.activiteas.Logic.PDFHelper;
import com.example.asus.activiteas.Logic.StoragePermissions;
import com.example.asus.activiteas.R;

public class HomeActivity extends AppCompatActivity {

    protected Button startButton;
    protected Button aboutButton;
    protected Button exitButton;
    protected Button addButton;
    protected Button deleteProd;
    protected StoragePermissions storagePermissions;
    protected Button savedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        storagePermissions = new StoragePermissions();
        savedList = (Button) findViewById(R.id.buttonSavedList);
        startButton = (Button) findViewById(R.id.buttonStart);
        aboutButton = (Button) findViewById(R.id.buttonAbout);
        exitButton = (Button) findViewById(R.id.buttonExit);
        addButton = (Button) findViewById(R.id.buttonAdd);
        deleteProd = (Button) findViewById(R.id.buttonDelete);

        storagePermissions.isStoragePermissionReadGranted(getApplicationContext(),this);

        savedList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewPDFActivity.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddProductActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
