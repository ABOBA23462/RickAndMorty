package com.example.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.App
import com.example.rickandmorty.data.db.daos.LocationDao
import com.example.rickandmorty.data.network.apiservices.LocationApiService
import com.example.rickandmorty.models.EpisodeModel
import com.example.rickandmorty.models.LocationsModel
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository
@Inject constructor(
    private val locationApiService: LocationApiService,
    private val locationDao: LocationDao
){

    fun fetchLocation(): MutableLiveData<RickAndMortyResponse<LocationsModel>> {
        val data: MutableLiveData<RickAndMortyResponse<LocationsModel>> = MutableLiveData()
        locationApiService.fetchLocation()
            .enqueue(object : Callback<RickAndMortyResponse<LocationsModel>> {

                override fun onResponse(
                    call: Call<RickAndMortyResponse<LocationsModel>>,
                    response: Response<RickAndMortyResponse<LocationsModel>>
                ) {
                    if (response.body() != null) {
                        response.body().let {
                            it?.let { it1 -> locationDao.insertAll(it1.results) }
                        }
                    }
                    data.value = response.body()
                }

                override fun onFailure(
                    call: Call<RickAndMortyResponse<LocationsModel>>,
                    t: Throwable
                ) {
                    data.value = null
                }
            })
        return data
    }

    fun getAll(): LiveData<List<LocationsModel>> {
        return locationDao.getAll()
    }


//    fun fetchLocation(): Flow<PagingData<LocationsModel>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 10,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = {
//                LocationPagingSource(App.locationApiService!!)
//            }).flow
//    }

    fun fetchLocationDetail(id: Int): MutableLiveData<LocationsModel> {
        val data: MutableLiveData<LocationsModel> = MutableLiveData()
        locationApiService.fetchLocationDetail(id)
            .enqueue(object : Callback<LocationsModel> {
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