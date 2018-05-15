package com.example.asus.activiteas.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.activiteas.R;
import com.example.asus.activiteas.logic.Options;

public class StartActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private TextView mTextView;
    private Button button;
    private SharedPreferences options;
    private Options people;
    final String SAVED_TEXT = "num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        people = new Options();
        settings();
        listener();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mTextView.setText(String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mTextView.setText(String.valueOf(seekBar.getProgress()));
    }

    private void settings(){
        mTextView = (TextView) findViewById(R.id.changes);
        mTextView.setText("0");
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(25);
        seekBar.setProgress(1);
        button = (Button) findViewById(R.id.buttonStart);
    }

    private void listener(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(mTextView.getText().toString());
                if(number>0) {
                    people.setLevel(number);
                    saveText(people.getLevel());
                    Intent intent = new Intent(StartActivity.this, EnterNameOfListActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),R.string.toast1,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void saveText(double num){
        options = getSharedPreferences("Options", MODE_PRIVATE);
        SharedPreferences.Editor editor = options.edit();
        editor.putString(SAVED_TEXT, Double.toString(num));
        editor.commit();
    }
}
