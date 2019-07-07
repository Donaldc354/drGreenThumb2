package com.example.drgreenthumb;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_WORLD_READABLE;

public class Storage extends AppCompatActivity {


    public void onCreate(SQLiteDatabase db) throws IOException {

    FileOutputStream fOut = openFileOutput("file name here", MODE_WORLD_READABLE);
    String str = "data";
    fOut.write(str.getBytes());
    fOut.close();

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void readFromStorage(){

    }

    public void writeToStorage(String fileString){

    }
}
