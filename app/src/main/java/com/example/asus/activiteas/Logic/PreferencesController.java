package com.example.asus.activiteas.Logic;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

public class PreferencesController {

    public PreferencesController(){

    }

    public static void save(String SAVED_TEXT, String data, Context context){
        SharedPreferences options = context.getSharedPreferences("Options", MODE_PRIVATE);
        SharedPreferences.Editor editor = options.edit();
        editor.putString(SAVED_TEXT, data);
        editor.apply();
    }

    public String load(String SAVED_TEXT, Context context){
        SharedPreferences sPref;
        sPref = context.getSharedPreferences("Options",MODE_PRIVATE);
        return sPref.getString(SAVED_TEXT, "");
    }

}
