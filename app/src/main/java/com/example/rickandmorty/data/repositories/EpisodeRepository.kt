package com.example.rickandmorty.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.App
import com.example.rickandmorty.data.repositories.pagingsources.EpisodePagingSource
import com.example.rickandmorty.models.EpisodeModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeRepository {

    fun fetchEpisode(): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EpisodePagingSource(App.episodeApiService!!)
            }).flow
    }

    fun fetchEpisodeDetail(id: Int): MutableLiveData<EpisodeModel> {
        val data: MutableLiveData<EpisodeModel> = MutableLiveData()
        App.episodeApiService?.fetchEpisodeDetail(id)
            ?.enqueue(object : Callback<EpisodeModel> {
                override fun onResponse(
                    call: Call<EpisodeModel>,
                    response: Response<EpisodeModel>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(
                    call: Call<EpisodeModel>,
                    t: Throwable
                ) {
                    data.value = null
                }
            })
        return data
    }
}