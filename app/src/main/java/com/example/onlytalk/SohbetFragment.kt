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

    private lateinit var tasarim: FragmentSohbetBinding
    private lateinit var adapter: FirebaseSohbetRVA
    private lateinit var datalar: ArrayList<RVADATA2>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        tasarim = FragmentSohbetBinding.inflate(inflater, container, false)
        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager=StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        datalar = ArrayList()

        var database = FirebaseDatabase.getInstance()
        var gonder = database.getReference("diyalog")
        var vt = VeriTabaniYardimcisi(requireContext())
        var kullanici = ""
        var kullanicigetir = KullaniciDataDao().bilgiGetir(vt)
        for (k in kullanicigetir) {
            kullanici = k.kullaniciadi
        }
        tasarim.gonder.setOnClickListener {
            gonder.push().setValue(FirebaseData(tasarim.girdi.text.toString(), kullanici))
            tasarim.girdi.text.clear()
        }



        gonder.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(ds: DataSnapshot) {
                datalar.clear()
                for (k in ds.children) {
                    var bilgi = k.getValue(FirebaseData::class.java)
                    if (bilgi != null) {
                        var key = k.key
                        var a1=bilgi.mesaj
                        var a2=bilgi.kullanici
                        datalar.add(RVADATA2(a1.toString(), a2.toString()))
                    }
                }

                adapter.notifyDataSetChanged()
                tasarim.rv.scrollToPosition(adapter.itemCount - 1)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        adapter = FirebaseSohbetRVA(requireContext(), datalar)
        tasarim.rv.adapter = adapter

        return tasarim.root
    }
}


