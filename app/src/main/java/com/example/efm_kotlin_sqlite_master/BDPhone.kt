package com.example.efm_kotlin_sqlite_master

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDPhone(context : Context) : SQLiteOpenHelper(context,"BD_AppPhone",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Phone_Table (id INTEGER PRIMARY KEY, name TEXT, price DOUBLE)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Phone")
        onCreate(db)
    }

    fun ajouterPhone(phone: Phone):Long {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", phone.id)
        values.put("name", phone.name)
        values.put("price", phone.price)
        return db.insert("Phone_Table", null, values)
    }

    fun getAll():ArrayList<Phone> {

        var PhoneList = ArrayList<Phone>()

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Phone_Table", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val price = cursor.getDouble(2)
                val phone = Phone(id,name,price)
                PhoneList.add(phone)
            } while (cursor.moveToNext())
        }
        return PhoneList
    }
    fun updatePhone(phone: Phone): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("id", phone.id)
        values.put("name", phone.name)
        values.put("price", phone.price)
        // Update app's row in database with the values of updated app
        return db.update("Phone_Table", values, "id = ?", arrayOf(phone.id.toString())).toLong()
    }

}