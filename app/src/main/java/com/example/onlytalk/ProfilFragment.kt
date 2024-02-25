package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlytalk.databinding.FragmentProfilBinding

class ProfilFragment : Fragment() {

    private lateinit var tasarim:FragmentProfilBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentProfilBinding.inflate(inflater,container,false)
        var vt=VeriTabaniYardimcisi(requireContext())
        var gelenData=KullaniciDataDao().bilgiGetir(vt)
        for(k in gelenData){
            tasarim.kullanici.text="Kullanıcı Adı: ${k.kullaniciadi}"
        }


        return tasarim.root
    }
}