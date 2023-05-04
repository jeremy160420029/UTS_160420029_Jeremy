package com.example.uts_160420029_jeremy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_160420029_jeremy.model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FoodCategoryList(application: Application): AndroidViewModel(application) {
    val foodDD = MutableLiveData<Food>()
    val foodLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(category:String){
        loadingLD.value = true
        foodLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/foods.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Food>>() { }.type
                val result = Gson().fromJson<List<Food>>(it, sType)

                result.forEach{
                    if(it.category.equals(category)){
                        foodDD.value = it
                    }
                }

                loadingLD.value = false
                Log.d("showvoley", foodDD.value.toString())
            },
            {
                Log.d("showvoley", it.toString())
                foodLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
}