package com.example.comarch.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Comarch on 2015-07-16.
 */
public class DatabaseExample extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExampleDatabase";
    private static final String TABLE_NAME = "DATA";
    private static final String DATA_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "( NAME TEXT, SURNAME TEXT);";
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    public DatabaseExample(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATA_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addData(Data data) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues(2);
        values.put("name", data.getName());
        values.put("surname", data.getSurname());
        long id = database.insert(TABLE_NAME, null, values);
        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Data getLastData(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(SELECT_ALL, null);
        if(c.getCount() == 0){
            return null;
        } else {
            c.moveToLast();
            return new Data(c.getString(0), c.getString(1));
        }
    }

}
