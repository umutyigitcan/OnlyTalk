package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.onlytalk.databinding.FragmentHesapAyarlarBinding

class HesapAyarlar : Fragment() {

    private lateinit var tasarim:FragmentHesapAyarlarBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentHesapAyarlarBinding.inflate(inflater,container,false)

        tasarim.kullaniciadidegistir.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_hesapAyarlar_to_kullaniciAdiDegistir)
        }
        tasarim.sifrenidegistir.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_hesapAyarlar_to_sifredegistir)
        }



        return tasarim.root
    }

}