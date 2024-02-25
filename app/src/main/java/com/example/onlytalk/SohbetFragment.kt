package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.onlytalk.databinding.FragmentSohbetBinding

class SohbetFragment : Fragment() {

    private lateinit var tasarim:FragmentSohbetBinding
    private lateinit var Datalar:ArrayList<RVADatalar>
    private lateinit var adapter:sohbetFragmentRVAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentSohbetBinding.inflate(inflater,container,false)
        Datalar= ArrayList()
        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager=StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        var g1=RVADatalar("umut",R.drawable.logo,"21:56")
        var g2=RVADatalar("yigit",R.drawable.logo,"16:56")


        Datalar.add(g1)
        Datalar.add(g2)

        adapter=sohbetFragmentRVAdapter(requireContext(),Datalar)
        tasarim.rv.adapter=adapter
        return tasarim.root
    }


}