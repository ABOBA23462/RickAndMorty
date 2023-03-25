package com.example.rickandmorty.ui.fragments.location.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.LocationRepository
import com.example.rickandmorty.models.LocationsModel
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {

    fun fetchLocationDetail(id: Int): MutableLiveData<LocationsModel> {
        return locationRepository.fetchLocationDetail(id)
    }
}