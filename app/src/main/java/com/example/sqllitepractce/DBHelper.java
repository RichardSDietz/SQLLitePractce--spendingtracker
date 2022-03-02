package com.example.sqllitepractce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Expense.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Expensedetails(category TEXT, amount REAL,description TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Expensedetails");
    }

    public Boolean insertexpensedata(String category, Float amount, String description)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", category);
        contentValues.put("amount", amount);
        contentValues.put("description", description);
        long result=DB.insert("Expensedetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateexpensedata(String category, Float amount, String description)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", category);
        contentValues.put("amount", amount);
        contentValues.put("description", description);
        Cursor cursor = DB.rawQuery("Select * from Expensedetails where description = ?", new String[] {description});
        if (cursor.getCount()>0){
            long result=DB.update("Expensedetails", contentValues, "description=?", new String [] {description});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deletedata(String description)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Expensedetails where description = ?", new String[] {description});
        if (cursor.getCount()>0){
            long result=DB.delete("Expensedetails", "description=?", new String [] {description});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Expensedetails", null);
        return cursor;
    }

}
