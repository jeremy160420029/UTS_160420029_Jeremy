package com.example.uts_160420029_jeremy.model

import com.google.gson.annotations.SerializedName

data class Food(
    val id:String?,
    @SerializedName("resto_name")
    val restoName:String?,
    @SerializedName("resto_review")
    val restoReview:String?,
    @SerializedName("resto_rating")
    val restoRating:String?,
    @SerializedName("photo_url")
    val photoUrl:String,
    @SerializedName("opening_hours")
    val openingHours:String,
    @SerializedName("distance")
    val distance:String
)
