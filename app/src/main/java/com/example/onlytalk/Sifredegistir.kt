package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.onlytalk.databinding.FragmentSifredegistirBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Sifredegistir : Fragment() {

    var kullaniciadi=""
    var kullanicisifre=""
    private lateinit var tasarim:FragmentSifredegistirBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentSifredegistirBinding.inflate(inflater,container,false)
        var database=FirebaseDatabase.getInstance()
        var data=database.getReference("Kullanicilar")

        var vt=VeriTabaniYardimcisi(requireContext())
        var bilgigetir=KullaniciDataDao().bilgiGetir(vt)
        for(k in bilgigetir){
            kullaniciadi=k.kullaniciadi
        }
        tasarim.onayla.setOnClickListener {
            data.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(ds: DataSnapshot) {
                    for(p in ds.children){
                        var bilgi=p.getValue(KullaniciKayit::class.java)
                        if(bilgi!=null){
                            var key=p.key
                            if(kullaniciadi==bilgi.kullaniciadi&&tasarim.girdibir.text.toString()==bilgi.kullanicisifre){
                                var updateInfo=HashMap<String,Any>()
                                updateInfo["kullaniciadi"]=kullaniciadi
                                updateInfo["kullanicisifre"]=tasarim.girdiiki.text.toString()
                                data.child(key.toString()).updateChildren(updateInfo)
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }


        return tasarim.root
    }

}