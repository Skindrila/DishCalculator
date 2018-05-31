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
import com.example.asus.activiteas.DataBase.DataBaseHelper;
import com.example.asus.activiteas.Logic.ProductPeace;
import com.example.asus.activiteas.Logic.VibrateService;
import com.example.asus.activiteas.R;

public class AddProductActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    protected TextView mTextView;
    protected EditText editText;
    protected Button button;
    protected ProductPeace products;
    protected DataBaseHelper productsHelper;
    protected VibrateService vibrateService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        vibrateService = new VibrateService();
        button = (Button) findViewById(R.id.productAddButton);
        editText = (EditText) findViewById(R.id.enterName);
        editText.setMaxLines(10);
        productsHelper = new DataBaseHelper();

        seekBarSettings();
        listener();
    }

    private void seekBarSettings(){
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

                if(!(editText.getText().toString().equals("")))
                if(Integer.parseInt(mTextView.getText().toString())>0) {
                    products = new ProductPeace();
                    products.setName(editText.getText().toString());
                    products.setPortion(Integer.parseInt(mTextView.getText().toString()));
                    productsHelper.saveToDataBase(products);
                    finish();
                    Intent intent = new Intent(AddProductActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    vibrateService.Vibrate(500,getApplicationContext());
                    Toast.makeText(getApplicationContext(),R.string.toast2,Toast.LENGTH_SHORT).show();
                }
                else {
                    vibrateService.Vibrate(500,getApplicationContext());
                    Toast.makeText(getApplicationContext(),R.string.toast3,Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}