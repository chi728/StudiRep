package com.example.foodsearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Recipe.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "RECIPE_TABLE";
    private static final String COLUMN_RECIPE_NAME = "RECIPE_NAME";
    private static final String COLUMN_RECIPE_INGREDIENT = "RECIPE_INGREDIENT";
    private static final String COLUMN_RECIPE_METHOD = "RECIPE_METHOD";
    private static final String COLUMN_RECIPE_COOKTIME = "RECIPE_COOKTIME";
    private static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECIPE_NAME + " TEXT," + COLUMN_RECIPE_INGREDIENT + " TEXT," + COLUMN_RECIPE_COOKTIME + " TEXT," + COLUMN_RECIPE_METHOD + " INT )";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //adding a recipe to a database and putting toast message to see if it works

    void addRecipe(String name, String ingredient, int cooktime, String method){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();//take pairs of calue and asscociate with them.
        cv.put(COLUMN_RECIPE_NAME, name);
        cv.put(COLUMN_RECIPE_INGREDIENT,ingredient);
        cv.put(COLUMN_RECIPE_METHOD,cooktime);
        cv.put(COLUMN_RECIPE_COOKTIME,method);

        long insert = db.insert(TABLE_NAME, null, cv);
        if(insert ==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
        }


    }

    //reads all the data and returns it. this funtion will be called in the main activity.

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =null;

        if(db != null){
            cursor = db.rawQuery(query,null);

        }
        return cursor;
    }



}
