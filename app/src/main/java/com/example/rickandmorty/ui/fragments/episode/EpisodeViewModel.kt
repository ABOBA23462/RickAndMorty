package com.example.rickandmorty.ui.fragments.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.models.EpisodeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository
) : ViewModel() {

    fun fetchEpisode() = episodeRepository.fetchEpisode()

    fun getAll() = episodeRepository.getAll()
}