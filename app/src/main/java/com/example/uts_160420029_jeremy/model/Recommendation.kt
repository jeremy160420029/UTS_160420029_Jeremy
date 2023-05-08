package com.example.uts_160420029_jeremy.model

import com.google.gson.annotations.SerializedName

data class Recommendation(
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
    val distance:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("category")
    val category:String,
    @SerializedName("ranking")
    val ranking:String
)
