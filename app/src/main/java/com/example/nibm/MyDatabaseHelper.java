package com.example.nibm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "NIBM.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "student_info";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_INDEX = " registration_number";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_MOBILE = "mobile_number";
    private static final String COLUMN_PARENT = "parent_number";



     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                COLUMN_INDEX + " TEXT, " +
                                COLUMN_NAME + " TEXT, " +
                                COLUMN_AGE + " TEXT, " +
                                COLUMN_GENDER + " TEXT, " +
                                COLUMN_MOBILE + " TEXT, " +
                                COLUMN_PARENT + " TEXT); ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    void addStudent (String index, String name, String age, String gender, String mobile, String home) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INDEX, index);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, age);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_MOBILE, mobile);
        cv.put(COLUMN_PARENT, home);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData (){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String index, String name, String age, String gender, String mobile, String home){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_INDEX, index);
         cv.put(COLUMN_NAME, name);
         cv.put(COLUMN_AGE, age);
         cv.put(COLUMN_GENDER, gender);
         cv.put(COLUMN_MOBILE, mobile);
         cv.put(COLUMN_PARENT, home);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }
}
