package com.raindragonn.favoritetracklist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.databinding.ItemTrackBinding

class TrackViewHolder(
    private val binding: ItemTrackBinding,
    private val favClickListener: (TrackItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup, favClickListener: (TrackItem) -> Unit): TrackViewHolder {
            val binding = ItemTrackBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return TrackViewHolder(binding, favClickListener)
        }
    }

    init {
        binding.ivFavorite.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                binding.trackItem?.let {
                    favClickListener.invoke(it)
                }
            }
        }
    }

    fun bind(track: TrackItem) {
        binding.trackItem = track
    }
}