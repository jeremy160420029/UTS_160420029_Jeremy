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
import com.example.uts_160420029_jeremy.model.Food
import com.example.uts_160420029_jeremy.model.Review
import com.example.uts_160420029_jeremy.util.loadImage

class ReviewListAdapter(val revList:ArrayList<Review>)
    : RecyclerView.Adapter<ReviewListAdapter.ReviewListHolder>()
{
    class ReviewListHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ubaya_kuliner_review_item, parent, false)
        return ReviewListHolder(view)
    }

    override fun getItemCount(): Int {
        return revList.size
    }

    fun updateRevList(newRevList: ArrayList<Review>) {
        revList.clear()
        revList.addAll(newRevList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ReviewListHolder, position: Int) {
        val txtNameRLI = holder.view.findViewById<TextView>(R.id.txtNameRLI)
        val txtReviewRLI = holder.view.findViewById<TextView>(R.id.txtReviewRLI)
        val txtRatingRLI = holder.view.findViewById<TextView>(R.id.txtRatingRLI)

        var imageRestoUK = holder.view.findViewById<ImageView>(R.id.imageRestoRLI)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarRLI)
        imageRestoUK.loadImage(revList[position].photoUrl, progressBar)

        txtNameRLI.text = revList[position].name
        txtReviewRLI.text = revList[position].review
        txtRatingRLI.text = revList[position].rating
    }
}