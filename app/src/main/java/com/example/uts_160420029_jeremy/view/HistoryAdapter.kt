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
import com.example.uts_160420029_jeremy.model.History
import com.example.uts_160420029_jeremy.util.loadImage

class HistoryAdapter(val historyList:ArrayList<History>)
    : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>()
{
    class HistoryHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ubaya_kuliner_history_item, parent, false)
        return HistoryHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    fun updateHisList(newHisList: ArrayList<History>) {
        historyList.clear()
        historyList.addAll(newHisList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val txtRestoNameHS = holder.view.findViewById<TextView>(R.id.txtRestoNameHS)
        val txtDateHS = holder.view.findViewById<TextView>(R.id.txtDateHS)
        val txtUserHS = holder.view.findViewById<TextView>(R.id.txtUserHS)
        val txtStatusHS = holder.view.findViewById<TextView>(R.id.txtStatusHS)
        val txtRatingHS = holder.view.findViewById<TextView>(R.id.txtRatingHS)

        var imageRestoRS = holder.view.findViewById<ImageView>(R.id.imageRestoHS)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarHS)
        imageRestoRS.loadImage(historyList[position].photoUrl, progressBar)

        txtRestoNameHS.text = historyList[position].restoName
        txtDateHS.text = historyList[position].date
        txtUserHS.text = historyList[position].user
        txtStatusHS.text = historyList[position].status
        txtRatingHS.text = historyList[position].rating
    }
}