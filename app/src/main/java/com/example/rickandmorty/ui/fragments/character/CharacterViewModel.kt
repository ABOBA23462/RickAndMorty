package com.example.rickandmorty.ui.fragments.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.models.CharacterModel

class CharacterViewModel : ViewModel() {

    private val characterRepository = CharacterRepository()

    fun fetchCharacters() = characterRepository.fetchCharacters().cachedIn(viewModelScope)

    fun fetchCharacterDetail(id: Int): MutableLiveData<CharacterModel> {
        return characterRepository.fetchCharacterDetail(id)
    }
}