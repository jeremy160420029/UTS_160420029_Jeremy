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
import com.example.uts_160420029_jeremy.model.Reservation
import com.example.uts_160420029_jeremy.util.loadImage

class ReservationAdapter(val resList:ArrayList<Reservation>)
    : RecyclerView.Adapter<ReservationAdapter.ReservationHolder>() {
    class ReservationHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ubaya_kuliner_reservation_item, parent, false)
        return ReservationHolder(view)
    }

    override fun getItemCount(): Int {
        return resList.size
    }

    fun updateResList(newResList: ArrayList<Reservation>) {
        resList.clear()
        resList.addAll(newResList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ReservationHolder, position: Int) {
        val txtRestoName = holder.view.findViewById<TextView>(R.id.txtRestoNameRS)
        val txtResDayRS = holder.view.findViewById<TextView>(R.id.txtResDayRS)
        val txtResHoursRS = holder.view.findViewById<TextView>(R.id.txtResHoursRS)
        val txtStatusRS = holder.view.findViewById<TextView>(R.id.txtStatusRS)
        val btnDetailRS = holder.view.findViewById<Button>(R.id.btnDetailRS)

        var imageRestoRS = holder.view.findViewById<ImageView>(R.id.imageRestoRS)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarRS)
        imageRestoRS.loadImage(resList[position].photoUrl, progressBar)

        txtRestoName.text = resList[position].restoName
        txtResDayRS.text = resList[position].day
        txtResHoursRS.text = resList[position].hours
        txtStatusRS.text = resList[position].status

        btnDetailRS.setOnClickListener {
            val action = ReservationListDirections.actionItemReservationToReservationDetail(resList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }
}