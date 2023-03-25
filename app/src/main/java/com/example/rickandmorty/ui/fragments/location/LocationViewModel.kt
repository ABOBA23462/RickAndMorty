package com.example.rickandmorty.ui.fragments.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.data.repositories.LocationRepository
import com.example.rickandmorty.models.LocationsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel
@Inject constructor(
    private val locationRepository: LocationRepository
): ViewModel() {

    fun fetchLocation() = locationRepository.fetchLocation()

    fun getAll() = locationRepository.getAll()
}