package com.example.rickandmorty.ui.fragments.character

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import kotlinx.coroutines.launch

class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewModel>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharacterViewModel by activityViewModels()
    private var characterAdapter = CharacterAdapter(this::onItemClick)

    override fun initialize() {
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
    }

    override fun setupObserves() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collect {
                characterAdapter.submitData(it)
            }
        }
    }

    private fun onItemClick(id: Int) {
        val action: NavDirections =
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(id)
        findNavController().navigate(action)
    }
}