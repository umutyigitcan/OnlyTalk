package com.example.onlytalk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class SohbetBaslatmaRVA(var mContext: Context,var list:ArrayList<SohbetBaslatmaData>):RecyclerView.Adapter<SohbetBaslatmaRVA.myCardViewTutucu>() {

    inner class myCardViewTutucu(view:View):ViewHolder(view){
        var sohbetsatircardview:CardView
        var resim:ImageView
        var kullaniciismi:TextView

        init{

            sohbetsatircardview=view.findViewById(R.id.sohbetsatircardview)
            resim=view.findViewById(R.id.resimsohbet)
            kullaniciismi=view.findViewById(R.id.kullaniciismi)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myCardViewTutucu {
        var tasarim=LayoutInflater.from(mContext).inflate(R.layout.sohbetbaslatsatircardview,parent,false)
        return myCardViewTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: myCardViewTutucu, position: Int) {
        var tutucu=list[position]
        holder.kullaniciismi.text=tutucu.kullanici
        holder.resim.setImageResource(tutucu.resim)
        holder.sohbetsatircardview.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_uygulamaButun_to_sohbetKonusmaFragment)
        }
    }



}