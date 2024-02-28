package com.example.onlytalk

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class KonusulanVeriTabaniYardimcisi(mContext:Context):SQLiteOpenHelper(mContext,"konusulan",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE konusulan(konusulansohbetid INTEGER PRIMARY KEY AUTOINCREMENT,konusulankisi TEXT);")
        db.execSQL("INSERT INTO konusulan(konusulankisi) VALUES('null')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS konusulan",null)
        onCreate(db)
    }
}