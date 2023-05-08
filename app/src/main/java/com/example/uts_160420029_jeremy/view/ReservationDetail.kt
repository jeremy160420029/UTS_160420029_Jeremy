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
import com.example.uts_160420029_jeremy.viewmodel.ReservationDetailVM

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: ReservationDetailVM

/**
 * A simple [Fragment] subclass.
 * Use the [ReservationDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReservationDetail : Fragment() {
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
        return inflater.inflate(R.layout.fragment_reservation_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var idRes = ""
        if(arguments != null){
            idRes = ReservationDetailArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(ReservationDetailVM::class.java)
        viewModel.fetch(idRes)

        val txtRestoNameD = view.findViewById<TextView>(R.id.textRestoNameRSD)
        val txtDayRSD = view.findViewById<TextView>(R.id.txtDayRSD)
        val txtHourRSD = view.findViewById<TextView>(R.id.txtHourRSD)
        val txtUserRSD = view.findViewById<TextView>(R.id.txtUserRSD)
        val txtAddressRSD = view.findViewById<TextView>(R.id.txtAddressRSD)
        val txtStatusRSD = view.findViewById<TextView>(R.id.txtStatusRSD)
        var imageResto = view.findViewById<ImageView>(R.id.imageRestoRSD)
        var progressBar = view.findViewById<ProgressBar>(R.id.progressBarRSD)

        observeViewModel(txtRestoNameD, txtDayRSD, txtHourRSD, txtUserRSD, txtAddressRSD, txtStatusRSD, imageResto, progressBar)
    }

    fun observeViewModel(txtRestoName: TextView, txtDayRSD: TextView, txtHourRSD: TextView, txtUserRSD: TextView, txtAddressRSD: TextView, txtStatusRSD: TextView, imageResto: ImageView, progressBar: ProgressBar) {
        viewModel.resDD.observe(viewLifecycleOwner, Observer {
            var resList = it
            txtRestoName.text = resList.restoName
            txtDayRSD.text = resList.day
            txtHourRSD.text = resList.hours
            txtUserRSD.text = resList.user
            txtAddressRSD.text = resList.address
            txtStatusRSD.text = resList.status

            imageResto.loadImage(resList.photoUrl, progressBar)
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReservationDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReservationDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}