package com.example.rickandmorty.ui.fragments.character.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.models.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    fun fetchCharacterDetail(id: Int): MutableLiveData<CharacterModel> {
        return characterRepository.fetchCharacterDetail(id)
    }
}