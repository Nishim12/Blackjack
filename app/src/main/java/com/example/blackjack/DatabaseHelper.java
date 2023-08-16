package com.example.blackjack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "your_database_name.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "your_table_name";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COLUMN1 = "column1";
    public static final String COLUMN_COLUMN3 = "column3";
    public static final String COLUMN_COLUMN5 = "column5";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_COLUMN1 + " INTEGER, " +
                    COLUMN_COLUMN3 + " INTEGER, " +
                    COLUMN_COLUMN5 + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // Implement database upgrade logic here if needed
    }
}

