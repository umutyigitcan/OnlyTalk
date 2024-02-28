package com.example.onlytalk

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.onlytalk.databinding.FragmentSohbetBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SohbetFragment : Fragment() {

    private lateinit var tasarim:FragmentSohbetBinding
    private lateinit var Datalar:ArrayList<RVADatalar>
    private lateinit var adapter:sohbetFragmentRVAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentSohbetBinding.inflate(inflater,container,false)
        Datalar= ArrayList()
        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager=StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        var vt=VeriTabaniYardimcisi(requireContext())
        var kullanicigetir=KullaniciDataDao().bilgiGetir(vt)
        var kullanici=""
        for(k in kullanicigetir){
            kullanici=k.kullaniciadi
        }
        var database=FirebaseDatabase.getInstance()
        var ekle=database.getReference(kullanici)

        ekle.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(ds: DataSnapshot) {
                Datalar.clear()
                for(k in ds.children){
                    var key=k.key
                    var kisi=k.getValue(FirebaseSohbetBaslatmaData::class.java)
                    if(kisi!=null){
                        Datalar.add(RVADatalar(kisi.isim.toString(),R.drawable.logo,kisi.saat.toString()))
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })





        adapter=sohbetFragmentRVAdapter(requireContext(),Datalar)
        tasarim.rv.adapter=adapter
        return tasarim.root
    }


}