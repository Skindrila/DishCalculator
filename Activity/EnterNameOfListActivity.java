package com.example.asus.activiteas.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.activiteas.R;

import org.w3c.dom.Text;

public class EnterNameOfListActivity extends AppCompatActivity {

    private Button button;
    private EditText text;
    private SharedPreferences options;
    final String SAVED_TEXT = "file_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name_of_list);

        button = (Button) findViewById(R.id.enterNameOfFile);
        text = (EditText) findViewById(R.id.textForFileName);
        text.setMaxLines(10);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText(text.getText().toString());
            }
        });
    }

    void saveText(String name){
        if(!(text.getText().toString().equals(""))) {
            options = getSharedPreferences("Options", MODE_PRIVATE);
            SharedPreferences.Editor editor = options.edit();
            editor.putString(SAVED_TEXT, name);
            Intent intent = new Intent(EnterNameOfListActivity.this, PresetActivity.class);
            startActivity(intent);
        }
        else Toast.makeText(getApplicationContext(),R.string.toast3,Toast.LENGTH_SHORT).show();
    }

}
