package com.example.onlytalk

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.onlytalk.databinding.FragmentKayitSayfasiBinding
import com.google.firebase.database.FirebaseDatabase

class KayitSayfasi : Fragment() {


    private lateinit var tasarim:FragmentKayitSayfasiBinding
    private var id=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentKayitSayfasiBinding.inflate(inflater,container,false)

        var vt=KayitVeriTabaniYardimcisi(requireContext())


        var database=FirebaseDatabase.getInstance()
        var refKisiler=database.getReference("Kullanicilar")
        tasarim.kayit.setOnClickListener {
            var g1 = tasarim.girdikullaniciadi.text.toString().trim()
            var g2 = tasarim.girdikullanicisifre.text.toString().trim()
            if (g1.length >= 6 && g2.length >= 6) {
                Toast.makeText(requireContext(),"Başarıyla kayıt olundu!",Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it).navigate(R.id.action_kayitSayfasi_to_girisSayfasi)
                KayitVeriTabaniYardimcisiDao().kisiEkle(vt, g1, g2)
                var getir = KayitVeriTabaniYardimcisiDao().kisiGetir(vt, g1)
                for (k in getir) {
                    id = k.kullaniciid
                }
                var yeniKisi = KullaniciKayit(g1, g2, id)
                refKisiler.push().setValue(yeniKisi)
                tasarim.girdikullaniciadi.text = Editable.Factory.getInstance().newEditable("")
                tasarim.girdikullanicisifre.text = Editable.Factory.getInstance().newEditable("")
            } else {
                Toast.makeText(requireContext(),"Kullanıcı adınız ve şifreniz 6 haneden küçük olmamalı!",Toast.LENGTH_SHORT).show()
                tasarim.girdikullaniciadi.text=Editable.Factory.getInstance().newEditable("")
                tasarim.girdikullanicisifre.text=Editable.Factory.getInstance().newEditable("")
            }

        }




        return tasarim.root
    }


}