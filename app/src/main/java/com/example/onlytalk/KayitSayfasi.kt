package com.example.onlytalk

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.onlytalk.databinding.FragmentKayitSayfasiBinding
import com.google.firebase.database.FirebaseDatabase

class KayitSayfasi : Fragment() {


    private lateinit var tasarim:FragmentKayitSayfasiBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentKayitSayfasiBinding.inflate(inflater,container,false)


        var database=FirebaseDatabase.getInstance()
        var refKisiler=database.getReference("Kullanicilar")
        tasarim.kayit.setOnClickListener {
            var g1=tasarim.girdikullaniciadi.text.toString()
            var g2=tasarim.girdikullanicisifre.text.toString()
            var yeniKisi=KullaniciKayit(g1,g2)
            refKisiler.push().setValue(yeniKisi)
            tasarim.girdikullaniciadi.text=Editable.Factory.getInstance().newEditable("")
            tasarim.girdikullanicisifre.text=Editable.Factory.getInstance().newEditable("")
        }




        return tasarim.root
    }


}