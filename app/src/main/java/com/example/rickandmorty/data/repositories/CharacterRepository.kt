package com.example.rickandmorty.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.App
import com.example.rickandmorty.data.repositories.pagingsources.CharacterPagingSource
import com.example.rickandmorty.models.CharacterModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {

    fun fetchCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(App.characterApiService!!)
            }).flow
    }

    fun fetchCharacterDetail(id: Int): MutableLiveData<CharacterModel> {
        val data: MutableLiveData<CharacterModel> = MutableLiveData()
        App.characterApiService?.fetchCharactersDetail(id)
            ?.enqueue(object : Callback<CharacterModel> {
                override fun onResponse(
                    call: Call<CharacterModel>,
                    response: Response<CharacterModel>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(
                    call: Call<CharacterModel>,
                    t: Throwable
                ) {
                    data.value = null
                }
            })
        return data
    }
}