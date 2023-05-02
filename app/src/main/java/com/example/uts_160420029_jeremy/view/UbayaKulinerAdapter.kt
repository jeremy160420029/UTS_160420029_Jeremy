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
import com.example.uts_160420029_jeremy.util.loadImage

class UbayaKulinerAdapter(val foodList:ArrayList<Food>)
    : RecyclerView.Adapter<UbayaKulinerAdapter.UKulinerViewHolder>()
{
    class UKulinerViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UKulinerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ubaya_kuliner_list_item, parent, false)
        return UKulinerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateFoodList(newFoodList: ArrayList<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UKulinerViewHolder, position: Int) {
        val txtRestoName = holder.view.findViewById<TextView>(R.id.txtRestoName)
        val txtRatingUK = holder.view.findViewById<TextView>(R.id.txtRatingUK)
        val txtOpeningHoursUK = holder.view.findViewById<TextView>(R.id.txtOpeningHoursUK)
        val txtReviewsUK = holder.view.findViewById<TextView>(R.id.txtReviewsUK)
        val txtDistanceUK = holder.view.findViewById<TextView>(R.id.txtDistanceUK)
        val btnDetailUK = holder.view.findViewById<Button>(R.id.btnDetailUK)

        var imageRestoUK = holder.view.findViewById<ImageView>(R.id.imageRestoUK)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imageRestoUK.loadImage(foodList[position].photoUrl, progressBar)

        txtRestoName.text = foodList[position].restoName
        txtRatingUK.text = foodList[position].restoRating
        txtOpeningHoursUK.text = foodList[position].openingHours
        txtReviewsUK.text = foodList[position].restoReview
        txtDistanceUK.text = foodList[position].distance

        btnDetailUK.setOnClickListener {
            val action = UbayaKulinerListDirections.actionUbayaKulinerDetail(foodList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }
}
