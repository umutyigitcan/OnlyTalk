package com.example.onlytalk

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeriTabaniYardimcisi(var mContext: Context):SQLiteOpenHelper(mContext,"kullanici",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE kullanici(kullaniciadi TEXT,oturum INTEGER);")
        db.execSQL("INSERT INTO kullanici(kullaniciadi, oturum) VALUES('null', 0);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS kullanici",null)
        onCreate(db)

    }
}