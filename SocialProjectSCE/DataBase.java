package com.example.socialprojectsce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AllUsers.db";
    private static final String TABLE_NAME = "UsersDB";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String TYPE = "type";
    private static final String AGE = "age";
    private static final String CITY = "city";
    private static final String SEX = "sex";
    private static final String DEPART = "depart";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private SQLiteDatabase DB;
    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + USERNAME + " TEXT PRIMARY KEY,"
                                                                                     +EMAIL + " TEXT,"
                                                                                     + PASSWORD + " TEXT,"
                                                                                     + TYPE + " TEXT, "
                                                                                     + AGE + " TEXT, "
                                                                                     + CITY + " TEXT, "
                                                                                     + SEX + " TEXT, "
                                                                                     + DEPART + " TEXT, "
                                                                                     + FIRSTNAME + " TEXT, "
                                                                                     + LASTNAME + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public void insert(String username,String email,String password,String type,String age,String city,String sex,String depart,String firstname,String lastname){
        DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);
        values.put(TYPE, type);
        values.put(AGE, age);
        values.put(CITY, city);
        values.put(SEX, sex);
        values.put(DEPART, depart);
        values.put(FIRSTNAME, firstname);
        values.put(LASTNAME, lastname);
        DB.insert(TABLE_NAME, null, values);
        DB.close();
    }
    public void update(String username,String email,String password,String type,String age,String city,String sex,String depart,String firstname,String lastname){
        DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME,username);
        values.put(EMAIL,email);
        values.put(PASSWORD,password);
        values.put(TYPE,type);
        values.put(AGE, age);
        values.put(CITY, city);
        values.put(SEX, sex);
        values.put(DEPART, depart);
        values.put(FIRSTNAME, firstname);
        values.put(LASTNAME, lastname);
        DB.update(TABLE_NAME,values,USERNAME + "=?" ,new String[] {username});
        DB.close();
    }
    public long delete(String username){
        DB = this.getReadableDatabase();
        return DB.delete(TABLE_NAME,USERNAME + "=" + username,null);
    }
    public Cursor getData(){
        DB = getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from " + TABLE_NAME,null);
        return cursor;
    }
}