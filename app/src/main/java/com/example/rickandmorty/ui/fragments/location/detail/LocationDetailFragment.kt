package com.example.rickandmorty.ui.fragments.location.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentLocationDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<FragmentLocationDetailBinding, LocationDetailViewModel>(R.layout.fragment_location_detail) {

    override val binding by viewBinding(FragmentLocationDetailBinding::bind)
    override val viewModel: LocationDetailViewModel by activityViewModels()
    private val args by navArgs<LocationDetailFragmentArgs>()

    override fun setupObserves() {
        viewModel.fetchLocationDetail(args.id).observe(viewLifecycleOwner) {
            binding.tvDetailLocationName.text = it.name
            binding.tvDetailLocationType.text = it.type
        }
    }
}