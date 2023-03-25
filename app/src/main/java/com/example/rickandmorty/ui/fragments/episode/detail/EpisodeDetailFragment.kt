package com.example.rickandmorty.ui.fragments.episode.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment :
    BaseFragment<FragmentEpisodeDetailBinding, EpisodeDetailViewModel>(R.layout.fragment_episode_detail) {

    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    override val viewModel: EpisodeDetailViewModel by viewModels()
    private val args by navArgs<EpisodeDetailFragmentArgs>()

    override fun setupObserves() {
        viewModel.fetchEpisodeDetail(args.id).observe(viewLifecycleOwner) {
            binding.tvDetailEpisodeName.text = it.name
            binding.tvDetailEpisode.text = it.episode
        }
    }
}