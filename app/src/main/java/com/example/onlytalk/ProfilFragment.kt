package com.example.onlytalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.onlytalk.databinding.FragmentProfilBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ProfilFragment : Fragment() {

    private lateinit var tasarim:FragmentProfilBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim= FragmentProfilBinding.inflate(inflater,container,false)
        var vt=VeriTabaniYardimcisi(requireContext())
        var kullaniciadimain=KullaniciDataDao().bilgiGetir(vt)
        var kullanici=""
        for (k in kullaniciadimain){
            tasarim.kullaniciadi.text=k.kullaniciadi.toString()
            kullanici=k.kullaniciadi.toString()
        }
        tasarim.cikisyap.setOnClickListener {
            KullaniciDataDao().cikisYap(vt)
            Navigation.findNavController(it).navigate(R.id.action_uygulamaButun_to_girisSayfasi)
            Toast.makeText(requireContext(),"Başarıyla çıkış yapıldı!",Toast.LENGTH_SHORT).show()
        }

        tasarim.ayardegistir.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_uygulamaButun_to_hesapAyarlar)
        }

        tasarim.hesapsil.setOnClickListener {
            var database=FirebaseDatabase.getInstance()
            var kullaniciislem=database.getReference("Kullanicilar")

            kullaniciislem.addValueEventListener(object :ValueEventListener{

                override fun onDataChange(ds: DataSnapshot) {
                    for(p in ds.children){
                        var kisi=p.getValue(KullaniciKayit::class.java)
                        if(kisi!=null){
                            var key=p.key
                            if(kullanici==kisi.kullaniciadi){
                                kullaniciislem.child(key.toString()).removeValue()
                                Navigation.findNavController(it).navigate(R.id.action_uygulamaButun_to_girisSayfasi)
                                Toast.makeText(requireContext(),"Hesabınız başarıyla silindi!",Toast.LENGTH_SHORT).show()

                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }



            })
        }




        return tasarim.root
    }
}