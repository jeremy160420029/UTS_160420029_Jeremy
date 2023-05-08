package com.example.uts_160420029_jeremy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420029_jeremy.R
import com.example.uts_160420029_jeremy.model.Promo
import com.example.uts_160420029_jeremy.util.loadImage

class PromoListAdapter(val promoList:ArrayList<Promo>)
    : RecyclerView.Adapter<PromoListAdapter.PromoListHolder>() {
    class PromoListHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ubaya_kuliner_promo_item, parent, false)
        return PromoListHolder(view)
    }

    override fun getItemCount(): Int {
        return promoList.size
    }

    fun updatePromoList(newPromoList: ArrayList<Promo>) {
        promoList.clear()
        promoList.addAll(newPromoList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PromoListHolder, position: Int) {
        val txtRestoName = holder.view.findViewById<TextView>(R.id.txtRestoNamePL)
        val txtPromoNamePL = holder.view.findViewById<TextView>(R.id.txtPromoNamePL)
        val txtDatePL = holder.view.findViewById<TextView>(R.id.txtDatePL)
        val txtPromoPL = holder.view.findViewById<TextView>(R.id.txtPromoPL)
        val btnDetailPL = holder.view.findViewById<Button>(R.id.btnDetailPL)

        var imageRestoRS = holder.view.findViewById<ImageView>(R.id.imageRestoPL)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarPL)
        imageRestoRS.loadImage(promoList[position].photoUrl, progressBar)

        txtRestoName.text = promoList[position].resto_name
        txtPromoNamePL.text = promoList[position].promo_name
        txtDatePL.text = promoList[position].date
        txtPromoPL.text = promoList[position].promo

        btnDetailPL.setOnClickListener {
            val action = PromoListDirections.actionPromoListToPromoListDetail(promoList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }
}