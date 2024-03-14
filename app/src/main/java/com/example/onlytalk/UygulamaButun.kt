package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.onlytalk.databinding.FragmentUygulamaButunBinding
import com.google.android.material.tabs.TabLayoutMediator

class UygulamaButun : Fragment() {


    private lateinit var tasarim:FragmentUygulamaButunBinding
    private lateinit var fragmentListesi:ArrayList<Fragment>
    private lateinit var fragmentBaslikListesi:ArrayList<String>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentUygulamaButunBinding.inflate(inflater,container,false)

        fragmentListesi= ArrayList()
        fragmentListesi.add(ArkadasEkleFragment())
        fragmentListesi.add(SohbetFragment())
        fragmentListesi.add(ProfilFragment())

        fragmentBaslikListesi= ArrayList()
        fragmentBaslikListesi.add("ÖZEL SOHBET")
        fragmentBaslikListesi.add("TOPLU SOHBET")
        fragmentBaslikListesi.add("Profil")

        val adapter=myViewPagerAdapter(requireActivity())
        tasarim.vp.adapter=adapter

        TabLayoutMediator(tasarim.tablayout,tasarim.vp){tab,position->
            tab.setText(fragmentBaslikListesi[position])
        }.attach()


        return tasarim.root
    }

    inner class myViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity){

        override fun createFragment(position: Int): Fragment {
            return fragmentListesi[position]
        }

        override fun getItemCount(): Int {
            return fragmentListesi.size
        }
    }


}