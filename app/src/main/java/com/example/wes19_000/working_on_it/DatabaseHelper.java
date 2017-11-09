package com.example.wes19_000.working_on_it;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "JobEntries.db";
    public static final String TABLE_NAME = "jobs";
    public static final String NAME_COLUMN = "name";
    public static final String ADDRESS_COLUMN = "address";
    public static final String PHONE_COLUMN = "phone";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String PAY_COLUMN = "pay";
    public static final String START_DATE_COLUMN = "startDate";
    public static final String END_DATE_COLUMN = "endDate";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " " +
            "(" + _ID + " INTEGER PRIMARY KEY, "
            + NAME_COLUMN
            + " TEXT, "
            + ADDRESS_COLUMN
            + " TEXT, "
            + PHONE_COLUMN
            + " TEXT, "
            + DESCRIPTION_COLUMN
            + " TEXT, "
            + PAY_COLUMN
            + " REAL, "
            + START_DATE_COLUMN
            + " TEXT, "
            + END_DATE_COLUMN
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
        contentValues.put(this.ADDRESS_COLUMN, job.getClientAddress());
        contentValues.put(this.PHONE_COLUMN, job.getClientPhone());
        contentValues.put(this.DESCRIPTION_COLUMN, job.getJobDescription());
        contentValues.put(this.PAY_COLUMN, job.getJobPay());
        contentValues.put(this.START_DATE_COLUMN, job.getStartDate());
        contentValues.put(this.END_DATE_COLUMN, job.getEndDate());
        db.insert(this.TABLE_NAME,null,contentValues);
        return true;
    }

    public Cursor getDataBYid(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT " + NAME_COLUMN +
                " FROM " +TABLE_NAME+
                " WHERE " +_ID+ " =" +id+"",null);
        result.moveToFirst();
        return result;
    }

    public Cursor getDataByDate(String startDate){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT " + NAME_COLUMN +
                " FROM " +TABLE_NAME+
                " WHERE " +START_DATE_COLUMN+ " = \"" +startDate+"\"",null);
        result.moveToFirst();
        return result;
    }


    //Get the name of jobs where the selected date is between a job's start date and end date.
    public Cursor getBetweenDates(String selectedDate){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + NAME_COLUMN +
                " FROM " +TABLE_NAME+
                " WHERE (" + END_DATE_COLUMN + " BETWEEN \"" +selectedDate+ "\"" + " AND " +END_DATE_COLUMN+ ")"
                + " AND (\"" +selectedDate+ "\" >= " +START_DATE_COLUMN+ ")"
                ,null);
        cursor.moveToFirst();
        Log.d("Querey Result ", String.valueOf(cursor.getColumnCount()));
        return cursor;
    }

    //Saves any changes in every column for the entry with the selectedName.
    public Cursor updateDataforEdit(String selectedName, String editName, String editAddress, String editPhone, String editDescription, double editPay, String editStart, String editEnd){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("UPDATE " + TABLE_NAME +
        " SET " +NAME_COLUMN+ " = \"" +editName+ "\", " +
                ADDRESS_COLUMN+ " = \"" +editAddress+ "\", " +
                PHONE_COLUMN+ " = \"" +editPhone+ "\", " +
                DESCRIPTION_COLUMN+ " = \"" +editDescription+ "\", " +
                PAY_COLUMN+ " = \"" +editPay+ "\", " +
                START_DATE_COLUMN+ " = \"" +editStart+ "\", " +
                END_DATE_COLUMN+ " = \"" +editEnd+ "\"" +
        " WHERE " +NAME_COLUMN+ " = \"" +selectedName+ "\"", null);
        cursor.moveToFirst();
        return cursor;
    }

    //Get all of the information for a job using the client's name.
    public Cursor getData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT " + NAME_COLUMN + ", " + ADDRESS_COLUMN + ", " + PHONE_COLUMN + ", " + DESCRIPTION_COLUMN + ", " + PAY_COLUMN + ", " +START_DATE_COLUMN+ ", " +END_DATE_COLUMN+
                " FROM " +TABLE_NAME+
                " WHERE " +NAME_COLUMN+ " = \"" +name+"\"",null);
        result.moveToFirst();
        return result;
    }
}
