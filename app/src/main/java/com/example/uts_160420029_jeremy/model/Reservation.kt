package com.example.uts_160420029_jeremy.model

import com.google.gson.annotations.SerializedName

data class Reservation(
    val id:String?,
    @SerializedName("resto_name")
    val restoName:String?,
    @SerializedName("day")
    val day:String?,
    @SerializedName("hours")
    val hours:String?,
    @SerializedName("photo_url")
    val photoUrl:String,
    @SerializedName("status")
    val status:String,
    @SerializedName("user")
    val user:String,
    @SerializedName("address")
    val address:String,
    @SerializedName("category")
    val category:String
)
