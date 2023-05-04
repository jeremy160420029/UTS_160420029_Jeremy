package com.example.uts_160420029_jeremy.model

import com.google.gson.annotations.SerializedName

data class Category(
    val id:String?,
    @SerializedName("name")
    val category_name:String?,
    @SerializedName("photo_url")
    val photoUrl:String
)
