package com.example.rickandmorty.ui.fragments.character.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.extensions.setImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(R.layout.fragment_character_detail) {

    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    override val viewModel: CharacterDetailViewModel by activityViewModels()
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun setupObserves() {
        viewModel.fetchCharacterDetail(args.id).observe(viewLifecycleOwner) {
            binding.tvDetailCharacterName.text = it.name
            binding.ivDetailCharacterPic.setImage(it.image)
        }
    }
}