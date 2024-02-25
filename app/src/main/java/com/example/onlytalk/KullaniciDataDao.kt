package com.example.onlytalk

class KullaniciDataDao {

    fun girisYap(vt:VeriTabaniYardimcisi,isim:String){
        var db=vt.writableDatabase
        db.execSQL("UPDATE kullanici SET kullaniciadi='$isim'")
        db.execSQL("UPDATE kullanici SET oturum=1")
        db.close()
    }

    fun cikisYap(vt:VeriTabaniYardimcisi){
        var db=vt.writableDatabase
        db.execSQL("UPDATE kullanici SET kullaniciadi='null'")
        db.execSQL("UPDATE kullanici SET oturum=0")
        db.close()
    }

    fun bilgiGetir(vt:VeriTabaniYardimcisi):ArrayList<KullaniciData>{
        var kisiBilgi=ArrayList<KullaniciData>()
        var db=vt.writableDatabase

        var cursor=db.rawQuery("SELECT * FROM kullanici",null)
        while (cursor.moveToNext()){
            var bilgi=KullaniciData(cursor.getString(cursor.getColumnIndexOrThrow("kullaniciadi")),
                cursor.getInt(cursor.getColumnIndexOrThrow("oturum")))
            kisiBilgi.add(bilgi)
        }

        return kisiBilgi

    }


}