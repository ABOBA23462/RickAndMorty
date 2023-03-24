package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemLocationsBinding
import com.example.rickandmorty.models.LocationsModel

class LocationAdapter(val onItemClick: (id: Int) -> Unit) :
    ListAdapter<LocationsModel, LocationAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemLocationsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { location ->
                    onItemClick(location.id)
                }
            }
        }

        fun onBind(item: LocationsModel?) = with(binding) {
            tvLocationName.text = item?.name
            tvLocationType.text = item?.type
            tvLocationDimension.text = item?.dimension
            tvLocationCreated.text = item?.created
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLocationsBinding.inflate(
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
        private val diffUtil = object : DiffUtil.ItemCallback<LocationsModel>() {
            override fun areItemsTheSame(
                oldItem: LocationsModel,
                newItem: LocationsModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: LocationsModel,
                newItem: LocationsModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}