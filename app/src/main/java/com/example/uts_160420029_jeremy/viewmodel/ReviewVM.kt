package com.example.uts_160420029_jeremy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_160420029_jeremy.model.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReviewVM(application: Application): AndroidViewModel(application) {
    val revListLD = MutableLiveData<ArrayList<Review>>()
    val revLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    var url = ""
    private var queue: RequestQueue? = null

    fun fetch(resto: String) {
        loadingLD.value = true
        revLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        if(resto == "Gudeg Pecel UBAYA"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/gudegpecel.json"
        } else if(resto == "Nasi Goreng Bakmi UBAYA"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/nasgorbak.json"
        } else if(resto == "Nasi Goreng Cumi UBAYA"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/nasgorcum.json"
        } else if(resto == "Anina"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/anina.json"
        } else if(resto == "Pizza Hut"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/phd.json"
        } else if(resto == "Burger King"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/bk.json"
        } else if(resto == "Depot Serry Kwetiau Medan"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/serry.json"
        } else if(resto == "Asean Kwetiau"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/asean.json"
        } else if(resto == "Sego Sambel Mak Yeye"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/makyeye.json"
        } else if(resto == "Zangrandi Ice Cream"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/zangradi.json"
        } else if(resto == "Moi Garden"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/moi.json"
        } else if(resto == "Soto Ayam Cak Har"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/soto.json"
        } else if(resto == "Carnis"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/carnis.json"
        } else if(resto == "Tokyo Resto"){
            url = "https://raw.githubusercontent.com/jeremy160420029/UTS_160420029_Jeremy/main/review/tokyo.json"
        }

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Review>>() { }.type
                val result = Gson().fromJson<List<Review>>(it, sType)
                revListLD.value = result as ArrayList<Review>?
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                revLoadErrorLD.value = false
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