package com.example.onlytalk

import SohbetBaslatmaRVA
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.onlytalk.databinding.FragmentArkadasEkleBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ArkadasEkleFragment : Fragment() {

    private lateinit var data:ArrayList<SohbetBaslatmaData>
    private lateinit var adapter:SohbetBaslatmaRVA
    private lateinit var tasarim:FragmentArkadasEkleBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentArkadasEkleBinding.inflate(inflater,container,false)
        tasarim.rv.setHasFixedSize(true)
        var vt=VeriTabaniYardimcisi(requireContext())
        var vt2=SohbetSQLite(requireContext())
        tasarim.rv.layoutManager=StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        data=ArrayList()
        tasarim.yazi.setOnClickListener{
            SohbetSQLiteDao().sohbetleriSil(vt2)
        }




        var database=FirebaseDatabase.getInstance()
        var kisiListe=database.getReference("Kullanicilar")

        kisiListe.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(ds: DataSnapshot) {
                data.clear()
                for(p in ds.children){

                    var kisi=p.getValue(KullaniciKayit::class.java)
                    if(kisi!=null){
                        var g1=kisi.kullaniciadi
                        data.add(SohbetBaslatmaData(g1.toString(),R.drawable.logo))
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        adapter=SohbetBaslatmaRVA(requireContext(),data)
        tasarim.rv.adapter=adapter
        return tasarim.root
    }


}