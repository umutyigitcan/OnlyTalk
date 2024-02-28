package com.example.onlytalk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SohbetKonusmaRVAdapter(var mContext:Context,var liste:ArrayList<SohbetKonusmaData>):RecyclerView.Adapter<SohbetKonusmaRVAdapter.myCardViewTutucu>(){
    var database=FirebaseDatabase.getInstance()
    var diyalog=database.getReference("diyalog")
    private var konusan=""

    inner class myCardViewTutucu(view:View):ViewHolder(view){

        var balonsol:TextView
        var balonsag:TextView

        init {
            balonsol=view.findViewById(R.id.balonsol)
            balonsag=view.findViewById(R.id.balonsag)
        }
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: myCardViewTutucu, position: Int) {
        var vt=VeriTabaniYardimcisi(mContext)
        var konusuanliste=KullaniciDataDao().bilgiGetir(vt)


        for(k in konusuanliste){
            konusan=k.kullaniciadi
        }
        var tutucu=liste[position]
        if(tutucu.kullanici==konusan){

            holder.balonsag.text=tutucu.mesaj
            holder.balonsol.visibility=View.INVISIBLE
            holder.balonsag.visibility=View.VISIBLE
        }else{
            holder.balonsol.text=tutucu.mesaj
            holder.balonsag.visibility=View.INVISIBLE
            holder.balonsol.visibility=View.VISIBLE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myCardViewTutucu {
        var tasarim=LayoutInflater.from(mContext).inflate(R.layout.sohbetbaloncuksatircardview,parent,false)
        return myCardViewTutucu(tasarim)
    }
}