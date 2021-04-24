package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234567890,'Samriddhi',9472.00,'samriddhi974@gmail.com','XXXXXXXXXXXX1234','ABC02873544')");
        db.execSQL("insert into user_table values(2345678901,'Manmeet',582.67,'manmeet0201@gmail.com','XXXXXXXXXXXX5678','DEF08265434')");
        db.execSQL("insert into user_table values(3456789012,'Nikita',1359.56,'nikita01@gmail.com','XXXXXXXXXXXX9101','GHI57654311')");
        db.execSQL("insert into user_table values(4567890123,'Khushi',1500.01,'khushi02@gmail.com','XXXXXXXXXXXX1213','JKL66543222')");
        db.execSQL("insert into user_table values(5678901234,'Chhaya',2603.48,'chhaya03@gmail.com','XXXXXXXXXXXX1415','MNO85432133')");
        db.execSQL("insert into user_table values(6789012345,'Salil',945.16,'salil04@gmail.com','XXXXXXXXXXXX1617','PQR56321044')");
        db.execSQL("insert into user_table values(7890123456,'Rohan',5936.00,'rohan05@gmail.com','XXXXXXXXXXXX1819','STU12210955')");
        db.execSQL("insert into user_table values(8901234567,'Sarangi',857.22,'sarangi06@gmail.com','XXXXXXXXXXXX2021','VWX89109866')");
        db.execSQL("insert into user_table values(9012345678,'Nitish',4398.46,'nitish07@gmail.com','XXXXXXXXXXXX2223','YZA32098777')");
        db.execSQL("insert into user_table values(1234567809,'Tanya',273.90,'tanya08@gmail.com','XXXXXXXXXXXX2425','BCD11987688')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
