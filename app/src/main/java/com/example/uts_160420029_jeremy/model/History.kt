package com.example.uts_160420029_jeremy.model

import com.google.gson.annotations.SerializedName

data class History(
    val id:String?,
    @SerializedName("resto_name")
    val restoName:String?,
    @SerializedName("date")
    val date:String?,
    @SerializedName("photo_url")
    val photoUrl:String,
    @SerializedName("status")
    val status:String,
    @SerializedName("user")
    val user:String,
    @SerializedName("rating")
    val rating:String
)
