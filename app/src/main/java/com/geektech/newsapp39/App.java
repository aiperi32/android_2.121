package com.geektech.newsapp39;

import android.app.Application;

import androidx.room.Room;

import com.geektech.newsapp39.room.AppDatabase;

public class App extends Application {


    public static AppDatabase appDatabase;
    private static Prefs prefs;


    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new Prefs(this);
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database.db")
                .allowMainThreadQueries()
                .build();

    }

    public static Prefs getPrefs() {
        return prefs;
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

}
