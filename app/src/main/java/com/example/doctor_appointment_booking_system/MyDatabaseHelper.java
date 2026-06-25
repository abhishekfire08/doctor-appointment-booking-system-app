package com.example.doctor_appointment_booking_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "signup.db";
    private static final int VERSION = 1;

    private static final String TABLE = "users";

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE " + TABLE + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "email TEXT UNIQUE, " +
                        "mob TEXT, " +
                        "pass TEXT, " +
                        "gender TEXT, " +
                        "dob TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // ✅ REGISTER USER
    public boolean insert(String name, String email, String mob,
                          String pass, String gender, String dob) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("mob", mob);
        values.put("pass", pass);
        values.put("gender", gender);
        values.put("dob", dob);

        long result = db.insert(TABLE, null, values);
        return result != -1;
    }

    // ✅ LOGIN CHECK (MAIN FUNCTION YOU NEED)
    public boolean checkUser(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE + " WHERE email=? AND pass=?",
                new String[]{email, password}
        );

        boolean exists = cursor.getCount() > 0;
        cursor.close();

        return exists;
    }
}