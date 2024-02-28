package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlytalk.databinding.FragmentProfilBinding
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ProfilFragment : Fragment() {

    private lateinit var tasarim:FragmentProfilBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentProfilBinding.inflate(inflater,container,false)
        var vt=VeriTabaniYardimcisi(requireContext())
        var gelenData=KullaniciDataDao().bilgiGetir(vt)
        for(k in gelenData){
            tasarim.kullanici.text="Kullanıcı Adı: ${k.kullaniciadi}"
        }

        var vt2=KonusulanVeriTabaniYardimcisi(requireContext())

        var liste=KonusulanVeriTabaniYardimcisiDao().konusulanKisiGetir(vt2)

        tasarim.tikla.setOnClickListener {
            var saat=LocalTime.now()
            var formatbicim=DateTimeFormatter.ofPattern("HH:mm")
            tasarim.saat.text=saat.format(formatbicim).toString()
        }
        for(k in liste){
            tasarim.konusulankisi.text="Konuşulan Kişi: ${k.konusulankisi}"
        }


        return tasarim.root
    }
}