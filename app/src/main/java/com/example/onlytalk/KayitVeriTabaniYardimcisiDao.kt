package com.example.onlytalk

class KayitVeriTabaniYardimcisiDao {

    fun kisiEkle(vt:KayitVeriTabaniYardimcisi,kullaniciadi:String,kullanicisifre: String){
        var db=vt.writableDatabase
        db.execSQL("INSERT INTO kayit(kullaniciadi,kullanicisifre) VALUES('$kullaniciadi','$kullanicisifre')")
        db.close()
    }

    fun kisiGetir(vt:KayitVeriTabaniYardimcisi,kullaniciadi: String):ArrayList<KayitData>{
        var liste=ArrayList<KayitData>()
        var db=vt.writableDatabase
        var cursor=db.rawQuery("SELECT * FROM kayit WHERE kullaniciadi='$kullaniciadi'",null)
        while (cursor.moveToNext()){
            var bilgi=KayitData(cursor.getString(cursor.getColumnIndexOrThrow("kullaniciadi")),
                cursor.getString(cursor.getColumnIndexOrThrow("kullanicisifre")),
                cursor.getInt(cursor.getColumnIndexOrThrow("kullaniciid")))
            liste.add(bilgi)
        }
        return liste
    }


}