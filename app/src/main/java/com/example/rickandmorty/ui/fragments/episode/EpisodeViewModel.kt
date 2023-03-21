package com.example.rickandmorty.ui.fragments.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.models.EpisodeModel

class EpisodeViewModel : ViewModel() {

    private val episodeRepository = EpisodeRepository()

    fun fetchEpisode() = episodeRepository.fetchEpisode().cachedIn(viewModelScope)

    fun fetchEpisodeDetail(id: Int): MutableLiveData<EpisodeModel> {
        return episodeRepository.fetchEpisodeDetail(id)
    }
}