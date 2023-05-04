package com.example.uts_160420029_jeremy.model

import com.google.gson.annotations.SerializedName

data class User(
    val id:String?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("email")
    val email:String?,
    @SerializedName("bod")
    val bod:String?,
    @SerializedName("photo_url")
    val photoUrl:String,
    @SerializedName("phone")
    val phone:String
)
