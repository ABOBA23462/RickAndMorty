package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.ItemCharactersBinding
import com.example.rickandmorty.models.CharacterModel

class CharacterAdapter(val onItemClick: (id: Int) -> Unit) :
    PagingDataAdapter<CharacterModel, CharacterAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { character ->
                    onItemClick(character.id)
                }
            }
        }

        fun onBind(item: CharacterModel?) = with(binding) {
            binding.tvCharacterName.text = item?.name
            tvCharacterStatus.text = item?.status
            tvCharacterSpecies.text = item?.species
            tvCharacterType.text = item?.type
            Glide.with(ivCharactersPicture.context).load(item?.image).into(ivCharactersPicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharactersBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}