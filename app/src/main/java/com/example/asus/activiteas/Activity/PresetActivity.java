package com.example.asus.activiteas.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.asus.activiteas.Logic.PreferencesController;
import com.example.asus.activiteas.R;
import com.example.asus.activiteas.Logic.Options;

public class PresetActivity extends AppCompatActivity {

    protected Button lightButton;
    protected Button mediumButton;
    protected Button hardButton;
    protected PreferencesController preferencesController;
    private Options mode;

    final String SAVED_TEXT = "mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset);

        preferencesController = new PreferencesController();
        lightButton = (Button) findViewById(R.id.light_button);
        mediumButton = (Button) findViewById(R.id.medium_button);
        hardButton = (Button) findViewById(R.id.hard_button);

        mode = new Options();

        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.setLevel(0.7);
                preferencesController.save(SAVED_TEXT, Double.toString(mode.getLevel()),getApplicationContext());
                next();
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.setLevel(1);
                preferencesController.save(SAVED_TEXT, Double.toString(mode.getLevel()),getApplicationContext());
                next();
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode.setLevel(1.5);
                preferencesController.save(SAVED_TEXT, Double.toString(mode.getLevel()),getApplicationContext());
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
