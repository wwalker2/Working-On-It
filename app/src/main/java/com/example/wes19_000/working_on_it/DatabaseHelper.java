package com.example.wes19_000.working_on_it;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "JobEntries.db";
    public static final String TABLE_NAME = "jobs";
    public static final String NAME_COLUMN = "name";
    public static final String START_DATE_COLUMN = "startDate";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " " +
            "(" + _ID + " INTEGER PRIMARY KEY, "
            + NAME_COLUMN
            + " TEXT, "
            + START_DATE_COLUMN
            + " TEXT)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context){
       super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertJob(JobEntry job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.NAME_COLUMN, job.getClientName());
        contentValues.put(this.START_DATE_COLUMN, job.getStartDate());
        db.insert(this.TABLE_NAME,null,contentValues);
        return true;
    }

    public Cursor getDataBYid(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT " + NAME_COLUMN + " FROM " +TABLE_NAME+" WHERE " +_ID+ " =" +id+"",null);
        result.moveToFirst();
        return result;
    }

    public Cursor getDataByDate(String startDate){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT " + NAME_COLUMN + " FROM " +TABLE_NAME+" WHERE " +START_DATE_COLUMN+ " = \"" +startDate+"\"",null);
        result.moveToFirst();
        return result;
    }
}
