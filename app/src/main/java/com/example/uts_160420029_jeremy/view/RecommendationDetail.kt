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
import com.example.uts_160420029_jeremy.viewmodel.DetailFoodVM
import com.example.uts_160420029_jeremy.viewmodel.RecommendationDetailVM

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: RecommendationDetailVM

/**
 * A simple [Fragment] subclass.
 * Use the [RecommendationDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecommendationDetail : Fragment() {
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
        return inflater.inflate(R.layout.fragment_recommendation_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var idRec = ""
        if(arguments != null){
            idRec = RecommendationDetailArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(RecommendationDetailVM::class.java)
        viewModel.fetch(idRec)

        val txtRestoNameD = view.findViewById<TextView>(R.id.textRestoNameRD)
        val txtDescResto = view.findViewById<TextView>(R.id.txtDescRestoRD)
        val txtReviewsResto = view.findViewById<TextView>(R.id.txtReviewsRestoRD)
        val txtRatingResto = view.findViewById<TextView>(R.id.txtRatingRestoRD)
        val txtDisResto = view.findViewById<TextView>(R.id.txtDisRestoRD)
        val txtRanking = view.findViewById<TextView>(R.id.txtRankingRD)
        var imageResto = view.findViewById<ImageView>(R.id.imageRestoRD)
        var progressBar = view.findViewById<ProgressBar>(R.id.progressBarRD)

        observeViewModel(txtRestoNameD, txtDescResto, txtReviewsResto, txtRatingResto, txtDisResto, txtRanking, imageResto, progressBar)
    }

    fun observeViewModel(txtRestoName: TextView, txtDescResto: TextView, txtReviewsResto: TextView, txtRatingResto: TextView, txtDisResto: TextView, txtRanking: TextView, imageResto: ImageView, progressBar: ProgressBar) {
        viewModel.recDD.observe(viewLifecycleOwner, Observer {
            var recList = it
            txtRestoName.text = recList.restoName
            txtDescResto.text = recList.description
            txtReviewsResto.text = recList.restoReview
            txtRatingResto.text = recList.restoRating
            txtDisResto.text = recList.distance
            txtRanking.text = recList.ranking

            imageResto.loadImage(recList.photoUrl, progressBar)
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecommendationDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecommendationDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}