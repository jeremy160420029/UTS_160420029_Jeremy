package com.example.uts_160420029_jeremy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_160420029_jeremy.model.Promo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PromoDetailVM(application: Application): AndroidViewModel(application) {
    val promoDD = MutableLiveData<Promo>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/promo.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Promo>>() { }.type
                val result = Gson().fromJson<List<Promo>>(it, sType)

                result.forEach{
                    if(it.id.equals(id)){
                        promoDD.value = it
                    }
                }

                Log.d("showvoley", promoDD.value.toString())
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
}