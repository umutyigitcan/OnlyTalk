
package com.example.onlytalk

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.onlytalk.databinding.FragmentGirisSayfasiBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GirisSayfasi : Fragment() {

    private lateinit var tasarim:FragmentGirisSayfasiBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentGirisSayfasiBinding.inflate(inflater,container,false)

        var database=FirebaseDatabase.getInstance()
        var refKisiler=database.getReference("Kullanicilar")

        tasarim.giris.setOnClickListener {

            refKisiler.addValueEventListener(object :ValueEventListener{//Tüm sonuçlar için sorgu yerine refKisiler yaz

                override fun onDataChange(ds: DataSnapshot) {
                    for(p in ds.children){
                        val kisi=p.getValue(KullaniciKayit::class.java)
                        if(kisi!=null){
                            val key =p.key
                            var g1=kisi.kullaniciadi.toString()
                            var g2=kisi.kullanicisifre.toString()
                            if(tasarim.girdikullaniciadi.text.toString()==g1&&tasarim.girdikullanicisifre.text.toString()==g2){
                                Navigation.findNavController(it).navigate(R.id.action_girisSayfasi_to_uygulamaButun)
                            }
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
        tasarim.kayit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_girisSayfasi_to_kayitSayfasi)
        }
        return tasarim.root
    }

}