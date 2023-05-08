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
import com.example.uts_160420029_jeremy.model.Recommendation
import com.example.uts_160420029_jeremy.util.loadImage

class RecommendationAdapter (val recList:ArrayList<Recommendation>)
    : RecyclerView.Adapter<RecommendationAdapter.RecommendationHolder>()
{
    class RecommendationHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ubaya_kuliner_recommendation_item, parent, false)
        return RecommendationHolder(view)
    }

    override fun getItemCount(): Int {
        return recList.size
    }

    fun updateRecList(newRecList: ArrayList<Recommendation>) {
        recList.clear()
        recList.addAll(newRecList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecommendationHolder, position: Int) {
        val txtRestoNameRL = holder.view.findViewById<TextView>(R.id.txtRestoNameRL)
        val txtRatingRL = holder.view.findViewById<TextView>(R.id.txtRatingRL)
        val txtOpeningHoursRL = holder.view.findViewById<TextView>(R.id.txtOpeningHoursRL)
        val txtReviewsRL = holder.view.findViewById<TextView>(R.id.txtReviewsRL)
        val txtDistanceRL = holder.view.findViewById<TextView>(R.id.txtDistanceRL)
        val txtTopRatingRL = holder.view.findViewById<TextView>(R.id.txtTopRatingRL)
        val btnDetailRL = holder.view.findViewById<Button>(R.id.btnDetailRL)

        var imageRestoRL = holder.view.findViewById<ImageView>(R.id.imageRestoRL)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarRL)
        imageRestoRL.loadImage(recList[position].photoUrl, progressBar)

        txtRestoNameRL.text = recList[position].restoName
        txtRatingRL.text = recList[position].restoRating
        txtOpeningHoursRL.text = recList[position].openingHours
        txtReviewsRL.text = recList[position].restoReview
        txtDistanceRL.text = recList[position].distance
        txtTopRatingRL.text = recList[position].ranking

        btnDetailRL.setOnClickListener {
            val action = RecommendationListDirections.actionToRecDetail(recList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }
}