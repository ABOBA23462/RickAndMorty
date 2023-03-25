package com.example.rickandmorty.ui.fragments.episode

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.ui.adapters.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment :
    BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private var episodeAdapter = EpisodeAdapter(this::onItemClick)

    override fun initialize() {
        binding.rvEpisode.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeAdapter
        }
    }

    override fun setupObserves() {
        if (isNetworkAvailable()) {
            viewModel.fetchEpisode().observe(viewLifecycleOwner) {
                episodeAdapter.submitList(it.results)
            }
        }else{
            viewModel.getAll().observe(viewLifecycleOwner){
                episodeAdapter.submitList(it)
            }
        }
    }

    private fun isNetworkAvailable(): Boolean{
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    private fun onItemClick(id: Int) {
        val action: NavDirections =
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(id)
        findNavController().navigate(action)
    }
}