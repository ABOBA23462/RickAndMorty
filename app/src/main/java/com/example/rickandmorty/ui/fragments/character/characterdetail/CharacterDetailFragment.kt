package com.example.rickandmorty.ui.fragments.character.characterdetail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.extensions.setImage
import com.example.rickandmorty.ui.fragments.character.CharacterViewModel

class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterViewModel>(R.layout.fragment_character_detail) {

    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    override val viewModel: CharacterViewModel by activityViewModels()
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun setupObserves() {
        viewModel.fetchCharacterDetail(args.id).observe(viewLifecycleOwner) {
            binding.tvDetailCharacterName.text = it.name
            binding.ivDetailCharacterPic.setImage(it.image)
        }
    }
}