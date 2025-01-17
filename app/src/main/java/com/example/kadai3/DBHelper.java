package com.example.kadai3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "kadai3.db", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "create table dr_data(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "area TEXT,"+
                        "pref TEXT,"+
//                        "city TEXT,"+
                        "d_street TEXT,"+
//                        "pronunciation TEXT,"+
                        "pronunciation TEXT);";
//                        "pronunciation TEXT);";
//                        "tips TEXT DEFAULT '');";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
