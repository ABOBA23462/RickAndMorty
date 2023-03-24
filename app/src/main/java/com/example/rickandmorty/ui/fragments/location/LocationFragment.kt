package com.example.rickandmorty.ui.fragments.location

import android.content.Context
import android.net.ConnectivityManager
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        if (isNetworkAvailable()) {
            viewModel.fetchLocation().observe(viewLifecycleOwner) {
                locationAdapter.submitList(it.results)
            }
        }else{
            viewModel.getAll().observe(viewLifecycleOwner){
                locationAdapter.submitList(it)
            }
        }
    }

    private fun onItemClick(id: Int) {
        val action: NavDirections =
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id)
        findNavController().navigate(action)
    }

    private fun isNetworkAvailable(): Boolean{
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeInfo = connectivityManager.activeNetworkInfo
        return activeInfo != null && activeInfo.isConnected
    }
}