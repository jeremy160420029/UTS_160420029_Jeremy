package com.example.uts_160420029_jeremy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uts_160420029_jeremy.R
import com.example.uts_160420029_jeremy.util.loadImage
import com.example.uts_160420029_jeremy.viewmodel.PromoDetailVM

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: PromoDetailVM

/**
 * A simple [Fragment] subclass.
 * Use the [PromoListDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class PromoListDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promo_list_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var idPromo = ""
        if(arguments != null){
            idPromo = PromoListDetailArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(PromoDetailVM::class.java)
        viewModel.fetch(idPromo)

        val textRestoNamePLD = view.findViewById<TextView>(R.id.textRestoNamePLD)
        val txtPromoNamePLD = view.findViewById<TextView>(R.id.txtPromoNamePLD)
        val txtDatePLD = view.findViewById<TextView>(R.id.txtDatePLD)
        val txtPromoPLD = view.findViewById<TextView>(R.id.txtPromoPLD)
        val txtDescPLD = view.findViewById<TextView>(R.id.txtDescPLD)
        var imageResto = view.findViewById<ImageView>(R.id.imageRestoPLD)
        var progressBar = view.findViewById<ProgressBar>(R.id.progressBarPLD)

        observeViewModel(textRestoNamePLD, txtPromoNamePLD, txtDatePLD, txtPromoPLD, txtDescPLD, imageResto, progressBar)
    }

    fun observeViewModel(textRestoNamePLD: TextView, txtPromoNamePLD: TextView, txtDatePLD: TextView, txtPromoPLD: TextView, txtDescPLD: TextView, imageResto: ImageView, progressBar: ProgressBar) {
        viewModel.promoDD.observe(viewLifecycleOwner, Observer {
            var promoList = it
            textRestoNamePLD.text = promoList.resto_name
            txtPromoNamePLD.text = promoList.promo_name
            txtDatePLD.text = promoList.date
            txtPromoPLD.text = promoList.promo
            txtDescPLD.text = promoList.description

            imageResto.loadImage(promoList.photoUrl, progressBar)
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PromoListDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PromoListDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}