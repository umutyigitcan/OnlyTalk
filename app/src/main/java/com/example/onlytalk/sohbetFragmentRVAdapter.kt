package com.example.onlytalk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class sohbetFragmentRVAdapter(var mContext:Context,var liste:List<RVADatalar>):RecyclerView.Adapter<sohbetFragmentRVAdapter.myViewPagerTutucu>() {

    inner class myViewPagerTutucu(view: View):ViewHolder(view){
        var satirCardView:CardView
        var resim:ImageView
        var isim:TextView
        var altyazi:TextView
        var saat:TextView

        init {
            satirCardView=view.findViewById(R.id.satirCardView)
            resim=view.findViewById(R.id.resim)
            isim=view.findViewById(R.id.isim)
            altyazi=view.findViewById(R.id.altyazi)
            saat=view.findViewById(R.id.saat)
        }
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: myViewPagerTutucu, position: Int) {
        var tutucu=liste[position]
        holder.saat.text=tutucu.saat
        holder.isim.text=tutucu.isim
        holder.resim.setImageResource(tutucu.resim)
        holder.satirCardView.setOnClickListener {
            Toast.makeText(mContext,"${tutucu.isim} adlı kullanıcının sohbetine tıklandı...",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewPagerTutucu {
        var tasarim=LayoutInflater.from(mContext).inflate(R.layout.sohbetfragmentcardview,parent,false)
        return myViewPagerTutucu(tasarim)
    }

}