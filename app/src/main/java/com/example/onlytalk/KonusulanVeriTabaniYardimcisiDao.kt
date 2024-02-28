package com.example.onlytalk

class KonusulanVeriTabaniYardimcisiDao {

    fun konusulanKisiDegistir(vt:KonusulanVeriTabaniYardimcisi,konusulankisi:String){
        var db=vt.writableDatabase
        db.execSQL("UPDATE konusulan SET konusulankisi='$konusulankisi'")
        db.close()
    }

    fun konusulanKisiGetir(vt:KonusulanVeriTabaniYardimcisi):ArrayList<KonusulanVeriTabaniYardimcisiData>{
        var db=vt.writableDatabase
        var konusulankisiisim=ArrayList<KonusulanVeriTabaniYardimcisiData>()
        var cursor=db.rawQuery("SELECT * FROM konusulan",null)
        while (cursor.moveToNext()){
            var konusulankisi=KonusulanVeriTabaniYardimcisiData(cursor.getString(cursor.getColumnIndexOrThrow("konusulankisi")))
            konusulankisiisim.add(konusulankisi)
        }
        return konusulankisiisim
    }





}