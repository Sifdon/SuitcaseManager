package edu.uta.cse5320.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Akshay on 3/16/2017.
 */

public class BagHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bags.db";
    public static final String TABLE_NAME = "bag_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "bagName";
    public static final String COL3 = "itemQuantity";
    public static final String COL4 = "imageUrl1";
    public static final String COL5 = "imageUrl2";
    public static final String COL6 = "imageUrl3";

    public BagHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT," +
                COL3 + " TEXT," +
                COL4 + " TEXT," +
                COL5 + " TEXT," +
                COL6 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addData(String bagName, int itemQuantity, String imageUrl1, String imageUrl2, String imageUrl3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, bagName);
        contentValues.put(COL3, itemQuantity);
        contentValues.put(COL4, imageUrl1);
        contentValues.put(COL5, imageUrl2);
        contentValues.put(COL6, imageUrl3);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        //if (result == -1) {
        return result;
        //} else {
        //    return result;
        //}
    }

    public boolean updateDetails(long rowId, String bagName, int itemQuantity, String imageUrl1, String imageUrl2, String imageUrl3)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, bagName);
        contentValues.put(COL3, itemQuantity);
        contentValues.put(COL4, imageUrl1);
        contentValues.put(COL5, imageUrl2);
        contentValues.put(COL6, imageUrl3);
        int i =  db.update(TABLE_NAME, contentValues, "ID=" + rowId, null);
        return i > 0;
    }

    //query for 1 week repeats
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

}