package com.example.rickandmorty.ui.fragments.episode

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.ui.adapters.EpisodeAdapter
import kotlinx.coroutines.launch

class EpisodeFragment :
    BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by activityViewModels()
    private var episodeAdapter = EpisodeAdapter(this::onItemClick)

    override fun initialize() {
        binding.rvEpisode.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeAdapter
        }
    }

    override fun setupObserves() {
        lifecycleScope.launch {
            viewModel.fetchEpisode().collect {
                episodeAdapter.submitData(it)
            }
        }
    }

    private fun onItemClick(id: Int) {
        val action: NavDirections =
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(id)
        findNavController().navigate(action)
    }
}