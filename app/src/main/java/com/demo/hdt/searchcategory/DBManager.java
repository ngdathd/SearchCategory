package com.demo.hdt.searchcategory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CATEGORY_ID + " TEXT NOT NULL, "
                + NAME + " TEXT NOT NULL, "
                + GROUP + " INTEGER NOT NULL, "
                + PARENT_ID + " TEXT DEFAULT NULL, "
                + PARENT_NAME + " TEXT DEFAULT NULL,"
                + NOTE + " TEXT DEFAULT NULL,"
                + LOGO + " TEXT DEFAULT NULL,"
                + CREATED_ID + " TEXT DEFAULT NULL,"
                + CREATED_NAME + " TEXT DEFAULT NULL,"
                + POSITION + " INTEGER DEFAULT NULL,"
                + IS_ACTIVE + " BOOLEAN DEFAULT NULL)";
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean isDatabaseExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = "SELECT " + ID + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count != 0;
    }

    public void insertListCategoryJson(List<CategoryJson> categoryJsonList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i = 0; i < categoryJsonList.size(); i++) {
            values.put(CATEGORY_ID, categoryJsonList.get(i).getCategoryId());
            values.put(NAME, categoryJsonList.get(i).getCategoryName());
            values.put(GROUP, categoryJsonList.get(i).getCategoryGroup());
            values.put(PARENT_ID, categoryJsonList.get(i).getCategoryRankParent().getId());
            values.put(PARENT_NAME, categoryJsonList.get(i).getCategoryRankParent().getName());
            values.put(NOTE, categoryJsonList.get(i).getCategoryNote());
            values.put(LOGO, categoryJsonList.get(i).getCategoryLogo());
            values.put(CREATED_ID, categoryJsonList.get(i).getCategoryAccountCreated().getId());
            values.put(CREATED_NAME, categoryJsonList.get(i).getCategoryAccountCreated().getName());
            values.put(POSITION, categoryJsonList.get(i).getCategoryPosition());
            values.put(IS_ACTIVE, categoryJsonList.get(i).getCategoryIsActive());

            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }

    public List<ItemCategory> getItemCategoryListByParentIdAndGroup(String parentId, int group) {
        List<ItemCategory> itemCategoryList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sqlQuery;
        Cursor cursor;

        if (parentId == null) {
            sqlQuery = "SELECT "
                    + CATEGORY_ID + ", "
                    + NAME + ", "
                    + GROUP + ", "
                    + PARENT_ID + ", "
                    + PARENT_NAME
                    + " FROM " + TABLE_NAME
                    + " WHERE " + PARENT_ID + " IS NULL"
                    + " AND " + GROUP + " = " + group;
        } else {
            sqlQuery = "SELECT "
                    + CATEGORY_ID + ", "
                    + NAME + ", "
                    + GROUP + ", "
                    + PARENT_ID + ", "
                    + PARENT_NAME
                    + " FROM " + TABLE_NAME + " WHERE " + PARENT_ID + " LIKE '" + parentId + "%'"
                    + " AND " + GROUP + " = " + group;
        }

        cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ItemCategory itemCategory = new ItemCategory();
                itemCategory.setCategoryId(cursor.getString(cursor.getColumnIndex(CATEGORY_ID)));
                itemCategory.setCategoryName(cursor.getString(cursor.getColumnIndex(NAME)));
                itemCategory.setCategoryGroup(cursor.getInt(cursor.getColumnIndex(GROUP)));
                itemCategory.setRankParentId(cursor.getString(cursor.getColumnIndex(PARENT_ID)));
                itemCategory.setRankParentName(cursor.getString(cursor.getColumnIndex(PARENT_NAME)));
                itemCategoryList.add(itemCategory);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemCategoryList;
    }

    public List<ItemCategory> getItemCategoryListByParentId(String parentId) {
        List<ItemCategory> itemCategoryList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sqlQuery;
        Cursor cursor;

        if (parentId == null) {
            sqlQuery = "SELECT "
                    + CATEGORY_ID + ", "
                    + NAME + ", "
                    + GROUP + ", "
                    + PARENT_ID + ", "
                    + PARENT_NAME
                    + " FROM " + TABLE_NAME
                    + " WHERE " + PARENT_ID + " IS NULL";
        } else {
            sqlQuery = "SELECT "
                    + CATEGORY_ID + ", "
                    + NAME + ", "
                    + GROUP + ", "
                    + PARENT_ID + ", "
                    + PARENT_NAME
                    + " FROM " + TABLE_NAME + " WHERE " + PARENT_ID + " = '" + parentId + "'";
        }

        cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ItemCategory itemCategory = new ItemCategory();
                itemCategory.setCategoryId(cursor.getString(cursor.getColumnIndex(CATEGORY_ID)));
                itemCategory.setCategoryName(cursor.getString(cursor.getColumnIndex(NAME)));
                itemCategory.setCategoryGroup(cursor.getInt(cursor.getColumnIndex(GROUP)));
                itemCategory.setRankParentId(cursor.getString(cursor.getColumnIndex(PARENT_ID)));
                itemCategory.setRankParentName(cursor.getString(cursor.getColumnIndex(PARENT_NAME)));
                itemCategoryList.add(itemCategory);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemCategoryList;
    }

    public List<ItemCategory> searchItemCategoryListByNameAndParentId(String name, String parentId) {
        List<ItemCategory> itemCategoryList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery;
        if (parentId == null) {
            sqlQuery = "SELECT "
                    + CATEGORY_ID + ", "
                    + NAME + ", "
                    + GROUP + ", "
                    + PARENT_ID + ", "
                    + PARENT_NAME
                    + " FROM " + TABLE_NAME + " WHERE " + NAME + " LIKE '" + name + "%'"
                    + " AND " + PARENT_ID + " IS NULL";
        } else {
            sqlQuery = "SELECT "
                    + CATEGORY_ID + ", "
                    + NAME + ", "
                    + GROUP + ", "
                    + PARENT_ID + ", "
                    + PARENT_NAME
                    + " FROM " + TABLE_NAME + " WHERE " + NAME + " LIKE '" + name + "%'"
                    + " AND " + PARENT_ID + " = '" + parentId + "'";
        }

        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ItemCategory itemCategory = new ItemCategory();
                itemCategory.setCategoryId(cursor.getString(cursor.getColumnIndex(CATEGORY_ID)));
                itemCategory.setCategoryName(cursor.getString(cursor.getColumnIndex(NAME)));
                itemCategory.setCategoryGroup(cursor.getInt(cursor.getColumnIndex(GROUP)));
                itemCategory.setRankParentId(cursor.getString(cursor.getColumnIndex(PARENT_ID)));
                itemCategory.setRankParentName(cursor.getString(cursor.getColumnIndex(PARENT_NAME)));
                itemCategoryList.add(itemCategory);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemCategoryList;
    }
}
