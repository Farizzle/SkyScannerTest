package com.example.skyscanner.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.skyscanner.R
import com.example.skyscanner.adapters.FlightAdapter
import com.example.skyscanner.ui.FlightViewModel
import com.example.skyscanner.util.Resource.Status.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_flight_list.*
import javax.inject.Inject

@AndroidEntryPoint
class FlightListFragment : Fragment(R.layout.fragment_flight_list) {

    private val viewModel: FlightViewModel by viewModels()

    private lateinit var flightsAdapter: FlightAdapter

    @Inject
    internal lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        subscribeToObservers()
        setupRecyclerView()
    }

    private fun setupAdapter() {
        flightsAdapter = FlightAdapter(glide)
    }

    private fun subscribeToObservers() {
        viewModel.allFlights.observe(viewLifecycleOwner, { products ->
            flightsAdapter.submitList(products)
        })
        viewModel.flightsStatus.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    LOADING -> progressBar.isVisible = true
                    SUCCESS -> progressBar.isVisible = false
                    ERROR -> {
                        progressBar.isVisible = false
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        rvFlights.apply {
            adapter = flightsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}