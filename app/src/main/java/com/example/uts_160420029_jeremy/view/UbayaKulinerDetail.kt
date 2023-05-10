package com.example.uts_160420029_jeremy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420029_jeremy.R
import com.example.uts_160420029_jeremy.util.loadImage
import com.example.uts_160420029_jeremy.viewmodel.DetailFoodVM

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: DetailFoodVM

/**
 * A simple [Fragment] subclass.
 * Use the [UbayaKulinerDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class UbayaKulinerDetail : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubaya_kuliner_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var detailFood = ""
        if(arguments != null){
            detailFood = UbayaKulinerDetailArgs.fromBundle(requireArguments()).detail
        }
        viewModel = ViewModelProvider(this).get(DetailFoodVM::class.java)
        viewModel.fetch(detailFood)

        val txtRestoNameD = view.findViewById<TextView>(R.id.textRestoNameDD)
        val txtDescResto = view.findViewById<TextView>(R.id.txtDescResto)
        val txtReviewsResto = view.findViewById<TextView>(R.id.txtReviewsResto)
        val txtRatingResto = view.findViewById<TextView>(R.id.txtRatingResto)
        val txtDisResto = view.findViewById<TextView>(R.id.txtDisResto)
        var imageResto = view.findViewById<ImageView>(R.id.imageResto)
        var progressBar = view.findViewById<ProgressBar>(R.id.progressBar4)

        var btnReviewFD = view.findViewById<Button>(R.id.btnReviewFD)

        observeViewModel(txtRestoNameD, txtDescResto, txtReviewsResto, txtRatingResto, txtDisResto, imageResto, progressBar, btnReviewFD)
    }

    fun observeViewModel(txtRestoName: TextView, txtDescResto: TextView, txtReviewsResto: TextView, txtRatingResto: TextView, txtDisResto: TextView, imageResto: ImageView, progressBar: ProgressBar, btnReviewFD: Button) {
        viewModel.foodDD.observe(viewLifecycleOwner, Observer {
            var foodList = it
            txtRestoName.text = foodList.restoName
            txtDescResto.text = foodList.description
            txtReviewsResto.text = foodList.restoReview
            txtRatingResto.text = foodList.restoRating
            txtDisResto.text = foodList.distance

            imageResto.loadImage(foodList.photoUrl, progressBar)

            btnReviewFD.setOnClickListener {
                val action = UbayaKulinerDetailDirections.actionUbayaKulinerDetailToReviewList(foodList.restoName.toString())
                Navigation.findNavController(it).navigate(action)
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UbayaKulinerDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                UbayaKulinerDetail().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}