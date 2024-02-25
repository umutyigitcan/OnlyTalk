package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.onlytalk.databinding.FragmentSohbetKonusmaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class SohbetKonusmaFragment : Fragment() {

    private lateinit var adapter: SohbetKonusmaRVAdapter
    private lateinit var mesajList: ArrayList<SohbetKonusmaData>
    private lateinit var tasarim: FragmentSohbetKonusmaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = FragmentSohbetKonusmaBinding.inflate(inflater, container, false)
        mesajList = ArrayList()
        adapter = SohbetKonusmaRVAdapter(requireContext(), mesajList)
        tasarim.rv.adapter = adapter
        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        var database = FirebaseDatabase.getInstance()
        var diyalog = database.getReference("diyalog")

        diyalog.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(ds: DataSnapshot) {
                mesajList.clear()

                for (p in ds.children) {
                    var tutulan = p.getValue(FirebaseSohbetData::class.java)
                    if (tutulan != null) {
                        var key = p.key
                        var a1 = tutulan.kullanici
                        var a2 = tutulan.mesaj
                        mesajList.add(SohbetKonusmaData(a2.toString(), a1.toString()))
                    }
                }


                adapter.notifyDataSetChanged()


                tasarim.rv.scrollToPosition(adapter.itemCount - 1)
            }
        })

        tasarim.gonder.setOnClickListener {
            var g1 = tasarim.girdi.text.toString()
            diyalog.push().setValue(FirebaseSohbetData(g1, "umut"))
            tasarim.girdi.text.clear()
        }

        return tasarim.root
    }
}
