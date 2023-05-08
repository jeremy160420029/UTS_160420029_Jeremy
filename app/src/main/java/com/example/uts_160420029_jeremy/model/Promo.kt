package com.example.uts_160420029_jeremy.model

import com.google.gson.annotations.SerializedName

data class Promo(
    val id:String?,
    @SerializedName("promo_name")
    val promo_name:String?,
    @SerializedName("resto_name")
    val resto_name:String?,
    @SerializedName("promo")
    val promo:String?,
    @SerializedName("photo_url")
    val photoUrl:String,
    @SerializedName("date")
    val date:String,
    @SerializedName("description")
    val description:String
)
