package com.example.uts_160420029_jeremy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.uts_160420029_jeremy.R
import com.example.uts_160420029_jeremy.viewmodel.ReviewVM

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: ReviewVM
private val revListAdapter = ReviewListAdapter(arrayListOf())

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewList.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewList : Fragment() {
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
        return inflater.inflate(R.layout.fragment_review_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var resto = ""
        if(arguments != null){
            resto = ReviewListArgs.fromBundle(requireArguments()).resto
        }
        viewModel = ViewModelProvider(this).get(ReviewVM::class.java)
        viewModel.fetch(resto)

        val recView = view.findViewById<RecyclerView>(R.id.recViewRL)
        val progressLoad = view.findViewById<ProgressBar>(R.id.progressLoadRL)
        val txtError = view.findViewById<TextView>(R.id.txtErrorRL)
        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutRL)

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = revListAdapter
        observeViewModel(txtError, recView, progressLoad)

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.fetch(resto)
            refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel(txtError: TextView, recView: RecyclerView, progressLoad: ProgressBar) {
        viewModel.revListLD.observe(viewLifecycleOwner, Observer {
            revListAdapter.updateRevList(it)
        })

        viewModel.revLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                recView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else {
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
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
         * @return A new instance of fragment ReviewList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}