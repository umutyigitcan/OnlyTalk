package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlytalk.databinding.FragmentKullaniciAdiDegistirBinding

class KullaniciAdiDegistir : Fragment() {

    private lateinit var tasarim:FragmentKullaniciAdiDegistirBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentKullaniciAdiDegistirBinding.inflate(inflater,container,false)

        return tasarim.root
    }


}