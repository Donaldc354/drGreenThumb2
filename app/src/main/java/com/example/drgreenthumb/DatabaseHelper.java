package com.example.drgreenthumb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "userID";
    public static final String COL_2 = "userEmail";
    public static final String COL_3 = "userPassword";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (userID INTEGER PRIMARY KEY AUTOINCREMENT, userEmail TEXT, userPassword TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String userEmail, String userPassword) {
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

    //Cursor class allows random read and write
    public User getUserData(String uEmail){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " +  TABLE_NAME + " WHERE userEmail=" + uEmail +"", null);
        User user = new User(res.getString(0), res.getString(1), res.getInt(2));
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


}
