package com.example.rickandmorty.ui.fragments.character

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewModel>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharacterViewModel by viewModels()
    private var characterAdapter = CharacterAdapter(this::onItemClick)

    override fun initialize() {
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
    }

    override fun setupObserves() {
        if (isNetworkAvailable()) {
            viewModel.fetchCharacters().observe(viewLifecycleOwner) {
                characterAdapter.submitList(it.results)
            }
        }else{
            viewModel.getAll().observe(viewLifecycleOwner){
                characterAdapter.submitList(it)
            }
        }
    }

    private fun onItemClick(id: Int) {
        val action: NavDirections =
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(id)
        findNavController().navigate(action)
    }

    private fun isNetworkAvailable(): Boolean{
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}