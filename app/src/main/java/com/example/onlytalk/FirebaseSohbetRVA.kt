package com.example.onlytalk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class FirebaseSohbetRVA(var mContext: Context,var liste:ArrayList<RVADATA2>):RecyclerView.Adapter<FirebaseSohbetRVA.myCardViewTutucu>() {
    var vt=VeriTabaniYardimcisi(mContext)

    inner class myCardViewTutucu(view:View):ViewHolder(view){
        val satirCardView:CardView
        val solyazankisi:TextView
        val sagyazankisi:TextView
        var solbaloncuk:TextView
        var sagbaloncuk:TextView
        init {
            satirCardView=view.findViewById(R.id.asatirCardView)
            solyazankisi=view.findViewById(R.id.asolyazankisi)
            sagyazankisi=view.findViewById(R.id.asagyazankisi)
            solbaloncuk=view.findViewById(R.id.asolbaloncuk)
            sagbaloncuk=view.findViewById(R.id.asagbaloncuk)
        }
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: myCardViewTutucu, position: Int) {
        val tutucu=liste[position]
        var vt=VeriTabaniYardimcisi(mContext)
        var gelenData=KullaniciDataDao().bilgiGetir(vt)
        var konusan=""
        for(k in gelenData){
            konusan=k.kullaniciadi
        }

        if(tutucu.kullanici==konusan){
            holder.sagbaloncuk.text=tutucu.mesaj
            holder.sagyazankisi.text=tutucu.kullanici
            holder.solbaloncuk.visibility=View.INVISIBLE
            holder.solyazankisi.visibility=View.INVISIBLE
            holder.sagbaloncuk.visibility=View.VISIBLE
            holder.sagyazankisi.visibility=View.VISIBLE
        }else{
            holder.solbaloncuk.text=tutucu.mesaj
            holder.solyazankisi.text=tutucu.kullanici
            holder.sagbaloncuk.visibility=View.INVISIBLE
            holder.sagyazankisi.visibility=View.INVISIBLE
            holder.solbaloncuk.visibility=View.VISIBLE
            holder.solyazankisi.visibility=View.VISIBLE
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myCardViewTutucu {
        val tasarim=LayoutInflater.from(mContext).inflate(R.layout.cardview,parent,false)
        return myCardViewTutucu(tasarim)
    }


}