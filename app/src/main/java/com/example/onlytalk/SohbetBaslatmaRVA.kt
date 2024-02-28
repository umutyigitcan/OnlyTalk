import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.onlytalk.FirebaseSohbetBaslatmaData
import com.example.onlytalk.KonusulanVeriTabaniYardimcisi
import com.example.onlytalk.KonusulanVeriTabaniYardimcisiDao
import com.example.onlytalk.KullaniciDataDao
import com.example.onlytalk.R
import com.example.onlytalk.SohbetBaslatmaData
import com.example.onlytalk.SohbetSQLite
import com.example.onlytalk.SohbetSQLiteDao
import com.example.onlytalk.VeriTabaniYardimcisi
import com.google.firebase.database.FirebaseDatabase

class SohbetBaslatmaRVA(
    var mContext: Context,
    var list: ArrayList<SohbetBaslatmaData>
) : RecyclerView.Adapter<SohbetBaslatmaRVA.myCardViewTutucu>() {

    var vt = VeriTabaniYardimcisi(mContext)
    var vt2 = KonusulanVeriTabaniYardimcisi(mContext)
    var vt3 = SohbetSQLite(mContext)

    var konusankisiisim = ""
    var konusulankisiisim = ""

    inner class myCardViewTutucu(view: View) : RecyclerView.ViewHolder(view) {
        var sohbetsatircardview: CardView
        var resim: ImageView
        var kullaniciismi: TextView

        init {
            sohbetsatircardview = view.findViewById(R.id.sohbetsatircardview)
            resim = view.findViewById(R.id.resimsohbet)
            kullaniciismi = view.findViewById(R.id.kullaniciismi)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myCardViewTutucu {
        val tasarim =
            LayoutInflater.from(mContext).inflate(R.layout.sohbetbaslatsatircardview, parent, false)
        return myCardViewTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: myCardViewTutucu, position: Int) {
        val tutucu = list[position]

        val konusan = KullaniciDataDao().bilgiGetir(vt)
        for (k in konusan) {
            konusankisiisim = k.kullaniciadi
        }

        val konusulan = KonusulanVeriTabaniYardimcisiDao().konusulanKisiGetir(vt2)
        for (k in konusulan) {
            konusulankisiisim = k.konusulankisi
        }

        if (konusankisiisim == konusulankisiisim) {
            holder.sohbetsatircardview.visibility = View.VISIBLE
        }

        holder.kullaniciismi.text = tutucu.kullanici
        holder.resim.setImageResource(tutucu.resim)

        holder.sohbetsatircardview.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val ekle = database.getReference(konusankisiisim)
            KonusulanVeriTabaniYardimcisiDao().konusulanKisiDegistir(vt2, tutucu.kullanici)

            ekle.push()
                .setValue(FirebaseSohbetBaslatmaData(konusulankisiisim, R.drawable.logo, "15:47"))


            SohbetSQLiteDao().sohbeteBasla(vt3, tutucu.kullanici, R.drawable.logo, "13:56")
            Navigation.findNavController(it).navigate(R.id.action_uygulamaButun_to_sohbetKonusmaFragment)
        }
    }
}
