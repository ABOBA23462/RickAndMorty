package com.example.rickandmorty.ui.fragments.episode.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.models.EpisodeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository
): ViewModel() {

    fun fetchEpisodeDetail(id: Int): MutableLiveData<EpisodeModel> {
        return episodeRepository.fetchEpisodeDetail(id)
    }
}