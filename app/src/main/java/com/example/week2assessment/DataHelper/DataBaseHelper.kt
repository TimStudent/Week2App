package com.example.week2assessment.DataHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY, " + VAL1
                + " TEXT," + VAL2 + " TEXT," + VAL3 + " TEXT," + VAL4 + " TEXT " + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun editData(V1: String, V2: String, V3: String, V4: String){
        val values = ContentValues()
        values.put(VAL1, V1)
        values.put(VAL2, V2)
        values.put(VAL3, V3)
        values.put(VAL4, V4)
        val db = this.writableDatabase
        db.update(TABLE_NAME, values, KEY, null)
        db.close()
    }
    // This method is for adding data in our database
    fun addData(V1 : String, V2: String, V3 : String, V4 : String){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(VAL1, V1)
        values.put(VAL2, V2)
        values.put(VAL3, V3)
        values.put(VAL4, V4)

        // here we are creating a
            // writable variable of
            // our database as we want to
            // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        const val DATABASE_NAME = "Events_Database"

        // below is the variable for database version
        const val DATABASE_VERSION = 1

        // below is the variable for table name
        const val TABLE_NAME = "Events_Table"

        //
        const val KEY = "Key"
        const val VAL1 = "Title"
        const val VAL2 = "Category"
        const val VAL3 = "Description"
        const val VAL4 = "Date"
    }
}