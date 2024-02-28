package com.example.onlytalk

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class KayitVeriTabaniYardimcisi(mContext:Context):SQLiteOpenHelper(mContext,"kayit",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE kayit(kullaniciid INTEGER PRIMARY KEY AUTOINCREMENT,kullaniciadi TEXT,kullanicisifre TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS kayit",null)
        onCreate(db)
    }
}