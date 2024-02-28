package com.example.onlytalk

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SohbetSQLite(mContext: Context):SQLiteOpenHelper(mContext,"secilensohbet",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE secilensohbet(isim TEXT,resim INTEGER,saat TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS secilensohbet",null)
        db.close()
    }
}