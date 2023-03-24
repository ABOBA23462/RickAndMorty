package com.example.rickandmorty.data.network.apiservices

import com.example.rickandmorty.models.LocationsModel
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("api/location")
     fun fetchLocation(
//        @Query("page") page: Int
    ): Call<RickAndMortyResponse<LocationsModel>>

    @GET("/api/location/{id}")
    fun fetchLocationDetail(@Path("id") id: Int): Call<LocationsModel>
}