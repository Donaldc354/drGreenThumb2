package com.example.drgreenthumb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PlantDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user_table";
    public static final String TABLE2_NAME = "favorites_table";
    public static final String TABLE3_NAME = "plant_table";
    public static final String COL_1 = "userID";
    public static final String COL_2 = "userEmail";
    public static final String COL_3 = "userPassword";


    public PlantDatabaseHelper(loginPage context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (userID INTEGER PRIMARY KEY AUTOINCREMENT, userEmail TEXT, userPassword TEXT )");
        db.execSQL("create table " + TABLE2_NAME + " (plantID INTEGER PRIMARY KEY, plantName TEXT)");
        db.execSQL("create table " + TABLE3_NAME + " (plantID INTEGER PRIMARY KEY, plantName TEXT, plantType TEXT, Toxicity TEXT, Height TEXT, lifeSpan TEXT, bloomPeriod TEXT, minTemp REAL, shadeTolerance TEXT, salinityTolerance TEXT, droughtTolerance TEXT, percipitationMin REAL, percipitationMax REAL, phMin REAL, phMax REAL )");
        //db.execSQL("create table" + TABLE3_NAME + "plantID INTEGER PRIMARY KEY AUTOINCREMENT")
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        onCreate(db);
    }


    /************USER FUNCTIONS*******************/

    public boolean insertUserData(String userEmail, String userPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, userEmail);
        contentValues.put(COL_3, userPassword);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE userEmail = ?", new String[]{email});
        if (cursor.getCount() > 0){
            return false;
        }
        else
            return true;
    }
    public Boolean checkPassword(String Email, String Password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE userEmail =" + Email, new String[]{Email});
        if (cursor.getString(3) != Password){
            return false;
        }
        else
            return true;
    }

    //Cursor class allows random read and write
    public User getUserData(String uEmail){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " +  TABLE_NAME + " WHERE userEmail=" + uEmail , null);
        User user = new User(res.getString(0), res.getString(1), res.getInt(2));
        res.close();
        return user;
    }
    /*
    public boolean updateUser(int userID, String userEmail, String userPassword){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userEmail", userEmail);
        contentValues.put("userPassword", userPassword);
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[] {Integer.toString(id)})
    }
    */

    public Boolean insertPlant(plant p1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("plantID", p1.speciesID);
        contentValues.put("plantName", p1.plantName);
        contentValues.put("plantType", p1.plantType);
        contentValues.put("Toxicity", p1.toxicity);
        contentValues.put("Height", p1.matureHeight_ft);
        contentValues.put("lifeSpan", p1.lifeSpan);
        contentValues.put("bloomPeriod", p1.bloomPeriod);
        contentValues.put("minTemp", p1.tempMin_f);
        contentValues.put("shadeTolerance", p1.shadeTolerance);
        contentValues.put("salinityTolerance", p1.salinityTolerance);
        contentValues.put("droughtTolerance", p1.droughtTolerance);
        contentValues.put("precipitationMin_in", p1.precipitationMin_in);
        contentValues.put("precipitationMax_in", p1.precipitationMax_in);
        contentValues.put("phMin", p1.pHmin);
        contentValues.put("phMax", p1.pHmax);
        long success = db.insert(TABLE3_NAME, null, contentValues);
        if (success == -1){
            return false;
        }
        else {
            return true;
        }
    }



    /***********************FAVORITES***************************/
    public Boolean insertFavorite(plant p1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("plantID", p1.speciesID);
        contentValues.put("plantName", p1.plantName);
        long success = db.insert(TABLE2_NAME, null, contentValues);
        if (success == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public plant getFavorite(String plantname){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE2_NAME + " WHERE plantName = ?", new String[] {plantname});
        plant p1 = new plant(cursor.getString(0), cursor.getInt(1),cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getFloat(5), cursor.getString(6), cursor.getString(7),cursor.getFloat(8),cursor.getString(9),cursor.getString(10), cursor.getString(11), cursor.getFloat(12),cursor.getFloat(13),cursor.getFloat(14),cursor.getFloat(15), cursor.getFloat(16));
        return p1;
    }

    public ArrayList<plant> getFavorites(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<plant> plantList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE2_NAME , null);
        while(cursor.moveToNext()){
            plant p1 = new plant(cursor.getString(0), cursor.getInt(1),cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getFloat(5), cursor.getString(6), cursor.getString(7),cursor.getFloat(8),cursor.getString(9),cursor.getString(10), cursor.getString(11), cursor.getFloat(12),cursor.getFloat(13),cursor.getFloat(14),cursor.getFloat(15), cursor.getFloat(16));
            plantList.add(p1);
        }
        return plantList;
    }

}
