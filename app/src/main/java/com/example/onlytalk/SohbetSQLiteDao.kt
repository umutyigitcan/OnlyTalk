package com.example.onlytalk

class SohbetSQLiteDao {

    fun sohbeteBasla(vt:SohbetSQLite,isim:String,resim:Int,saat:String){
        var db=vt.writableDatabase
        db.execSQL("INSERT INTO secilensohbet(isim,resim,saat) VALUES('$isim',$resim,'$saat')")
        db.close()
    }

    fun verileriGetir(vt: SohbetSQLite):ArrayList<SohbetSQLiteData>{
        var db=vt.writableDatabase
        var liste=ArrayList<SohbetSQLiteData>()
        var cursor=db.rawQuery("SELECT * FROM secilensohbet",null)
        while (cursor.moveToNext()){
            var data=SohbetSQLiteData(cursor.getString(cursor.getColumnIndexOrThrow("isim")),
                cursor.getInt(cursor.getColumnIndexOrThrow("resim")),
                cursor.getString(cursor.getColumnIndexOrThrow("saat")))
            liste.add(data)

        }
        return liste
    }

    fun sohbetleriSil(vt:SohbetSQLite){
        var db=vt.writableDatabase
        db.execSQL("DELETE FROM secilensohbet")
        db.close()
    }


}