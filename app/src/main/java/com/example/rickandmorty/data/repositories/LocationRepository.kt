package com.example.rickandmorty.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.App
import com.example.rickandmorty.data.repositories.pagingsources.LocationPagingSource
import com.example.rickandmorty.models.LocationsModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationRepository {

    fun fetchLocation(): Flow<PagingData<LocationsModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LocationPagingSource(App.locationApiService!!)
            }).flow
    }

    fun fetchLocationDetail(id: Int): MutableLiveData<LocationsModel> {
        val data: MutableLiveData<LocationsModel> = MutableLiveData()
        App.locationApiService?.fetchLocationDetail(id)
            ?.enqueue(object : Callback<LocationsModel> {
                override fun onResponse(
                    call: Call<LocationsModel>,
                    response: Response<LocationsModel>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(
                    call: Call<LocationsModel>,
                    t: Throwable
                ) {
                    data.value = null
                }
            })
        return data
    }
}