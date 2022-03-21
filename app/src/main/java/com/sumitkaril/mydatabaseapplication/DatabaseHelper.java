package com.sumitkaril.mydatabaseapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DemoDb.db";
    public static final String TABLE_NAME = "student";
    public static final String Col_1 = "ID";
    public static final String Col_2 ="NAME";
    public static final String Col_3 = "PHONE";
    public static final String Col_4 ="ADDRESS";
    public Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE INTEGER, ADDRESS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, Integer phone, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, name);
        contentValues.put(Col_3, phone);
        contentValues.put(Col_4, address);
        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == 0){
            return false;
        }else {
            return true;
        }
    }

    @SuppressLint("Recycle")
    public int deleteRawThroughID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, "ID="+id,null);
        Toast.makeText(context, "Integer value = "+result, Toast.LENGTH_LONG).show();
       // db.rawQuery("DELETE FROM "+TABLE_NAME+"WHERE ID= "+id, null);
        db.close();
        return result;
    }

    @SuppressLint("Range")
    public ArrayList<StudentModel> readData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorStudent = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<StudentModel> studentArrayList = new ArrayList<>();

        if (cursorStudent.moveToFirst()){
            do {
                studentArrayList.add(new StudentModel(cursorStudent.getInt(0),cursorStudent.getString(1),
                        cursorStudent.getInt(2), cursorStudent.getString(3)));
            } while (cursorStudent.moveToNext());
        }
        cursorStudent.close();
        return studentArrayList;
    }

}
