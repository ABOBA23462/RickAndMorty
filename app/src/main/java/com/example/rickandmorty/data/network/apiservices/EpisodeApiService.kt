package com.example.rickandmorty.data.network.apiservices

import com.example.rickandmorty.models.EpisodeModel
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApiService {

    @GET("api/episode")
     fun fetchEpisode(
//        @Query("page") page: Int
    ): Call<RickAndMortyResponse<EpisodeModel>>

    @GET("/api/episode/{id}")
    fun fetchEpisodeDetail(@Path("id") id: Int): Call<EpisodeModel>
}