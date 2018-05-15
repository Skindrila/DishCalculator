package com.example.asus.activiteas.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.activiteas.DBPackage.Product;
import com.example.asus.activiteas.R;
import com.example.asus.activiteas.logic.Products;

public class AddProductActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private static final String LOG_TAG = "my_tag";
    private TextView mTextView;
    private EditText editText;
    private Button button;
    private Products products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        button = (Button) findViewById(R.id.productAddButton);
        editText = (EditText) findViewById(R.id.enterName);
        editText.setMaxLines(10);


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
        mTextView = (TextView) findViewById(R.id.portionSeekbarText);
        mTextView.setText("0");
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar2);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(500);
        seekBar.setProgress(1);
    }

    private void listener(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(mTextView.getText().toString());
                if(!(editText.getText().toString().equals("")))
                if(number>0) {
                    products = new Products();
                    products.setName(editText.getText().toString());
                    products.setPortion(number);
                    saveDB(products);
                    Intent intent = new Intent(AddProductActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),R.string.toast2,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),R.string.toast3,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveDB(Products products){
        Product db = new Product(products.getName(), products.getPortion());
        db.save();
    }
}