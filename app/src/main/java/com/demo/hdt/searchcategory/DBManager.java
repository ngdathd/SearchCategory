package com.demo.hdt.searchcategory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "categories";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "categories";
    private static final String ID = "id";
    private static final String CATEGORY_ID = "category_id";
    private static final String NAME = "name";
    private static final String GROUP = "group_id";
    private static final String PARENT_ID = "parent_id";
    private static final String PARENT_NAME = "parent_name";
    private static final String NOTE = "note";
    private static final String LOGO = "logo";
    private static final String CREATED_ID = "created_id";
    private static final String CREATED_NAME = "created_name";
    private static final String POSITION = "position";
    private static final String IS_ACTIVE = "is_active";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CATEGORY_ID + " TEXT NOT NULL, "
                + NAME + " TEXT NOT NULL, "
                + GROUP + " INTEGER NOT NULL, "
                + PARENT_ID + " TEXT DEFAULT NULL, "
                + PARENT_NAME + "TEXT DEFAULT NULL,"
                + NOTE + "TEXT DEFAULT NULL,"
                + LOGO + "TEXT DEFAULT NULL,"
                + CREATED_ID + "TEXT DEFAULT NULL,"
                + CREATED_NAME + "TEXT DEFAULT NULL,"
                + POSITION + "INTEGER DEFAULT NULL,"
                + IS_ACTIVE + "BOOLEAN DEFAULT NULL)";
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
