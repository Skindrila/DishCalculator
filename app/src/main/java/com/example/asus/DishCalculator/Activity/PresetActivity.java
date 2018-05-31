package com.example.asus.activiteas.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.asus.activiteas.Logic.PreferencesController;
import com.example.asus.activiteas.R;

public class PresetActivity extends AppCompatActivity {

    protected Button lightButton;
    protected Button mediumButton;
    protected Button hardButton;
    protected PreferencesController preferencesController;
    public static final double mode1 = 0.7;
    public static final double mode2 = 1;
    public static final double mode3 = 1.5;
    public static final String SAVED_TEXT = "mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset);

        preferencesController = new PreferencesController();
        lightButton = (Button) findViewById(R.id.light_button);
        mediumButton = (Button) findViewById(R.id.medium_button);
        hardButton = (Button) findViewById(R.id.hard_button);


        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesController.save(SAVED_TEXT, Double.toString(mode1),getApplicationContext());
                next();
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesController.save(SAVED_TEXT, Double.toString(mode2),getApplicationContext());
                next();
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesController.save(SAVED_TEXT, Double.toString(mode3),getApplicationContext());
                next();
            }
        });
    }

    void next(){
        Intent intent = new Intent(PresetActivity.this, ListActivity.class);
        finish();
        startActivity(intent);
    }
}
