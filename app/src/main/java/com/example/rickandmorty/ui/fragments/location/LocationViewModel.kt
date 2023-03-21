package com.example.rickandmorty.ui.fragments.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.LocationRepository
import com.example.rickandmorty.models.LocationsModel

class LocationViewModel : ViewModel() {

    private val locationRepository = LocationRepository()

    fun fetchLocation() = locationRepository.fetchLocation().cachedIn(viewModelScope)

    fun fetchLocationDetail(id: Int): MutableLiveData<LocationsModel> {
        return locationRepository.fetchLocationDetail(id)
    }
}