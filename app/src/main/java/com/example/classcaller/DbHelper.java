package com.example.classcaller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "Classcaller.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
         DB.execSQL("create Table todo(Id INTEGER primary key AUTOINCREMENT, Topic TEXT,Subject_Name TEXT,Day TEXT,Time TEXT, Note TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists todo");
    }

    public Boolean insertTodoData(String topic,String subjectName,String day,String time,String note){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Topic",topic);
        contentValues.put("Subject_Name",subjectName);
        contentValues.put("Day",day);
        contentValues.put("Time",time);
        contentValues.put("Note",note);

        long result = DB.insert("todo",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }

    }


    public Cursor ShowTodoData(){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from todo",null);

        return cursor;

    }
}
