package com.example.rickandmorty.ui.fragments.episode.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmorty.ui.fragments.episode.EpisodeViewModel

class EpisodeDetailFragment :
    BaseFragment<FragmentEpisodeDetailBinding, EpisodeViewModel>(R.layout.fragment_episode_detail) {

    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    override val viewModel: EpisodeViewModel by activityViewModels()
    private val args by navArgs<EpisodeDetailFragmentArgs>()

    override fun setupObserves() {
        viewModel.fetchEpisodeDetail(args.id).observe(viewLifecycleOwner) {
            binding.tvDetailEpisodeName.text = it.name
            binding.tvDetailEpisode.text = it.episode
        }
    }
}