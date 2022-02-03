package com.geektech.newsapp39;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);

    }
    public void saveBoardStarts(){
        preferences.edit().putBoolean("isBoardShown",true).apply();
    }

    public boolean isBoardShown() {
        String text = preferences.getString("text","Default");
        return preferences.getBoolean("isBoardShown",false);
    }

}
