package com.example.uts_160420029_jeremy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_160420029_jeremy.model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FoodList(application: Application): AndroidViewModel(application) {
    val foodListLD = MutableLiveData<ArrayList<Food>>()
    val foodLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        foodLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/jeremy160420029/testing/main/foods1.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Food>>() { }.type
                val result = Gson().fromJson<List<Food>>(it, sType)
                foodListLD.value = result as ArrayList<Food>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                foodLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}