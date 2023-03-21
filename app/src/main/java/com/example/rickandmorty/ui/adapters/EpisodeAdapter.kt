package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemEpisodesBinding
import com.example.rickandmorty.models.EpisodeModel

class EpisodeAdapter(val onItemClick: (id: Int) -> Unit) :
    PagingDataAdapter<EpisodeModel, EpisodeAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemEpisodesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { episode ->
                    onItemClick(episode.id)
                }
            }
        }

        fun onBind(item: EpisodeModel?) = with(binding) {
            tvEpisodeName.text = item?.name
            tvEpisodeAirData.text = item?.air_date
            tvEpisodeCode.text = item?.episode
            tvEpisodeCreated.text = item?.created
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEpisodesBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<EpisodeModel>() {
            override fun areItemsTheSame(
                oldItem: EpisodeModel, newItem: EpisodeModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: EpisodeModel, newItem: EpisodeModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
