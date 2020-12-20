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
         DB.execSQL("create Table Todo(Id INTEGER primary key AUTOINCREMENT, Topic TEXT,Subject_Name TEXT,Day TEXT,Time TEXT, Note TEXT)");
        DB.execSQL("create Table Routine(Id INTEGER primary key AUTOINCREMENT,Course_Name TEXT,Course_Code TEXT,Room_Number INTEGER,Course_Teacher TEXT,Day TEXT, Start_Time TEXT,End_Time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Todo");
        DB.execSQL("drop Table if exists Routine");
    }

    //todo Section

    public Boolean insertTodoData(String topic,String subjectName,String day,String time,String note){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Topic",topic);
        contentValues.put("Subject_Name",subjectName);
        contentValues.put("Day",day);
        contentValues.put("Time",time);
        contentValues.put("Note",note);

        long result = DB.insert("Todo",null,contentValues);
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

    //Routine Section

    public Boolean insertRoutineData(String coursename,String coursecode,String room,String courseteacher,String day,String starttime,String endtime){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Course_Name",coursename);
        contentValues.put("Course_Code",coursecode);
        contentValues.put("Room_Number",room);
        contentValues.put("Course_Teacher",courseteacher);
        contentValues.put("Day",day);
        contentValues.put("Start_Time",starttime);
        contentValues.put("End_Time",endtime);

        long result = DB.insert("Routine",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }

    }

    public Cursor ShowRoutineData() {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Routine", null);
        return cursor;
    }
}
