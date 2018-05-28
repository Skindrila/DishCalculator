package com.example.asus.activiteas.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.asus.activiteas.R;
import com.example.asus.activiteas.Logic.Options;
import com.example.asus.activiteas.Logic.Preferences;

public class PresetActivity extends AppCompatActivity {

    private Button lightButton;
    private Button mediumButton;
    private Button hardButton;
    private SharedPreferences options;
    private Preferences preferences;
    private Options mode;

    final String SAVED_TEXT = "mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset);

        //preferences = new Preferences();
        lightButton = (Button) findViewById(R.id.light_button);
        mediumButton = (Button) findViewById(R.id.medium_button);
        hardButton = (Button) findViewById(R.id.hard_button);

        mode = new Options();

        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.setLevel(0.7);
                saveText(SAVED_TEXT, Double.toString(mode.getLevel()));
                next();
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.setLevel(1);
                saveText(SAVED_TEXT, Double.toString(mode.getLevel()));
                next();
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.setLevel(1.5);
                saveText(SAVED_TEXT, Double.toString(mode.getLevel()));
                next();
            }
        });

    }

    void next(){
        Intent intent = new Intent(PresetActivity.this, ListActivity.class);
        startActivity(intent);
    }

    public void saveText(String SAVED_TEXT, String data){
        options = getSharedPreferences("Options", MODE_PRIVATE);
        SharedPreferences.Editor editor = options.edit();
        editor.putString(SAVED_TEXT, data);
        editor.commit();
    }
}
