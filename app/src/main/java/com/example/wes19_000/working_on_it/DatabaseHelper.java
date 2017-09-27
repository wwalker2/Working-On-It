package com.example.wes19_000.working_on_it;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wes19_000 on 9/26/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "JobDatabase.db";
    private static final String TABLE_NAME = "jobs";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";

    public DatabaseHelper(Context context){
       super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table jobs" + "(name)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS jobs");
        onCreate(db);
    }

    public boolean insertJob(JobEntry job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", job.getClientName());
        db.insert("jobs",null,contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from id="+id+"",null);
        return result;
    }
}
