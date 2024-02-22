package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlytalk.databinding.FragmentUygulamaButunBinding

class UygulamaButun : Fragment() {


    private lateinit var tasarim:FragmentUygulamaButunBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentUygulamaButunBinding.inflate(inflater,container,false)


        return tasarim.root
    }


}