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

    public static final String DB_NAME = "JobDatabase.db";
    public static final String TABLE_NAME = "jobs";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    public DatabaseHelper(Context context){
       super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table jobs" + "(id integer primary key, name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS jobs");
        onCreate(db);
    }

    public boolean insertJob(JobEntry job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.NAME_COLUMN, job.getClientName());
        db.insert(this.TABLE_NAME,null,contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT name FROM jobs WHERE id=" +id+"",null);
        return result;
    }
}
