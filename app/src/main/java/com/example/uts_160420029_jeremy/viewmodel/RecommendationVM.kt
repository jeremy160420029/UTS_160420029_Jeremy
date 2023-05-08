package com.example.uts_160420029_jeremy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_160420029_jeremy.model.Recommendation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecommendationVM(application: Application): AndroidViewModel(application) {
    val recListLD = MutableLiveData<ArrayList<Recommendation>>()
    val recLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        recLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/recommendation.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Recommendation>>() { }.type
                val result = Gson().fromJson<List<Recommendation>>(it, sType)
                recListLD.value = result as ArrayList<Recommendation>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                recLoadErrorLD.value = false
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