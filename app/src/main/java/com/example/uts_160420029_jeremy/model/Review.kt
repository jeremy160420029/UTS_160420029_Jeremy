package com.example.uts_160420029_jeremy.model

import com.google.gson.annotations.SerializedName

data class Review(
    val id:String?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("photo_url")
    val photoUrl:String,
    @SerializedName("review")
    val review:String,
    @SerializedName("rating")
    val rating:String
)
