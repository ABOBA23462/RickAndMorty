package com.example.rickandmorty.ui.fragments.location

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.ui.adapters.LocationAdapter
import kotlinx.coroutines.launch

class LocationFragment :
    BaseFragment<FragmentLocationBinding, LocationViewModel>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private var locationAdapter = LocationAdapter(this::onItemClick)

    override fun initialize() {
        binding.rvLocation.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationAdapter
        }
    }

    override fun setupObserves() {
        lifecycleScope.launch {
            viewModel.fetchLocation().collect {
                locationAdapter.submitData(it)
            }
        }
    }

    private fun onItemClick(id: Int) {
        val action: NavDirections =
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id)
        findNavController().navigate(action)
    }
}