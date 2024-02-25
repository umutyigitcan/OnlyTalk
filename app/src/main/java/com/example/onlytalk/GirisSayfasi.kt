
package com.example.onlytalk

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
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

        var vt=VeriTabaniYardimcisi(requireContext())



        val database=FirebaseDatabase.getInstance()
        val refKisiler=database.getReference("Kullanicilar")



        tasarim.giris.setOnClickListener {

            refKisiler.addValueEventListener(object :ValueEventListener{

                override fun onDataChange(ds: DataSnapshot) {
                    for(p in ds.children){
                        val kisi=p.getValue(KullaniciKayit::class.java)
                        if(kisi!=null){
                            val key =p.key
                            var g1=kisi.kullaniciadi.toString()
                            var g2=kisi.kullanicisifre.toString()

                            if(tasarim.girdikullaniciadi.text.toString().trim()==g1&&tasarim.girdikullanicisifre.text.toString().trim()==g2){
                                Navigation.findNavController(it).navigate(R.id.action_girisSayfasi_to_uygulamaButun)
                                KullaniciDataDao().girisYap(vt,g1)

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