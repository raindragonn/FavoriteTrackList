package com.raindragonn.favoritetracklist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.databinding.ItemTrackBinding

class TrackListAdapter : ListAdapter<TrackItem, TrackListAdapter.ViewHolder>(differ) {
    companion object {
        val differ = object : DiffUtil.ItemCallback<TrackItem>() {
            override fun areItemsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean {
                return oldItem.trackId == newItem.trackId
            }

            override fun areContentsTheSame(oldItem: TrackItem, newItem: TrackItem): Boolean {
                return oldItem.trackName == newItem.trackName &&
                        oldItem.artistName == newItem.artistName &&
                        oldItem.isFavorite == newItem.isFavorite
            }
        }
    }

    var favoriteClickListener: ((TrackItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTrackBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemTrackBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.ivFavorite.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    favoriteClickListener?.invoke(getItem(adapterPosition))
                }
            }
        }

        fun bind(track: TrackItem) {
            binding.trackItem = track
        }
    }
}