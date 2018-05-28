package com.example.asus.activiteas.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.asus.activiteas.Logic.PreferencesController;
import com.example.asus.activiteas.Logic.VibrateService;
import com.example.asus.activiteas.R;

public class EnterNameOfListActivity extends AppCompatActivity {

    protected Button button;
    protected EditText text;
    protected PreferencesController preferencesController;
    public VibrateService vibrator;
    final String SAVED_TEXT = "file_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name_of_list);

        preferencesController = new PreferencesController();
        button = (Button) findViewById(R.id.enterNameOfFile);
        text = (EditText) findViewById(R.id.textForFileName);
        text.setMaxLines(10);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(text.getText().toString().equals(""))) {
                    preferencesController.save(SAVED_TEXT, text.getText().toString(), getApplicationContext());
                    Intent intent = new Intent(EnterNameOfListActivity.this, PresetActivity.class);
                    finish();
                    startActivity(intent);
                }
                else{
                    vibrator= new VibrateService();
                    vibrator.Vibrate(500, getApplicationContext());
                    Toast.makeText(getApplicationContext(),R.string.toast3,Toast.LENGTH_SHORT).show();}
            }
        });
    }
}
