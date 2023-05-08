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
import com.example.uts_160420029_jeremy.model.Category
import com.example.uts_160420029_jeremy.util.loadImage

class FoodCategoryAdapter (val categoryList:ArrayList<Category>)
    : RecyclerView.Adapter<FoodCategoryAdapter.FoodCategoryHolder>()
{
    class FoodCategoryHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ubaya_kuliner_category_item, parent, false)
        return FoodCategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun updateCatList(newCatList: ArrayList<Category>) {
        categoryList.clear()
        categoryList.addAll(newCatList)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: FoodCategoryHolder, position: Int) {
        val txtCategoryName = holder.view.findViewById<TextView>(R.id.txtCategoryName)

        var imageCategory = holder.view.findViewById<ImageView>(R.id.imageCategory)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)

        var btnCategory = holder.view.findViewById<Button>(R.id.btnCategory)

        imageCategory.loadImage(categoryList[position].photoUrl, progressBar)

        txtCategoryName.text = categoryList[position].category_name

        btnCategory.setOnClickListener {
            val action = FoodCategoryDirections.actionToCatList(categoryList[position].category_name.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }


}