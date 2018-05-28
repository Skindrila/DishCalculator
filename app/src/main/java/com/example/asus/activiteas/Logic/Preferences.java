package com.example.asus.activiteas.Logic;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;


public class Preferences extends AppCompatActivity {
    public SharedPreferences options;

    public Preferences(){

    }

    public void saveText(String SAVED_TEXT, String data){
        options = getSharedPreferences("Options", MODE_PRIVATE);
        SharedPreferences.Editor editor = options.edit();
        editor.putString(SAVED_TEXT, data);
        editor.commit();
    }

    public Double loadNumOfPerson(){
        final String SAVED_TEXT = "num";
        SharedPreferences sPref;
        sPref = getSharedPreferences("Options",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        return Double.parseDouble(savedText);
    }

    public String loadNameOfFile(){
        final String SAVED_TEXT = "file_name";
        SharedPreferences sPref;
        sPref = getSharedPreferences("Options",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        return savedText;
    }

    public Double loadLevel(){
        final String SAVED_TEXT = "mode";
        SharedPreferences sPref;
        sPref = getSharedPreferences("Options",MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        return Double.parseDouble(savedText);
    }
}
