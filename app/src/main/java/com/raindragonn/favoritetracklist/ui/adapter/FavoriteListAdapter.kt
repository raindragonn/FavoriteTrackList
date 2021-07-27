package com.raindragonn.favoritetracklist.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.raindragonn.favoritetracklist.data.model.TrackItem

class FavoriteListAdapter(
    private val favClickListener: (TrackItem) -> Unit
) : ListAdapter<TrackItem, TrackViewHolder>(differ) {
    companion object {
        /**
         * 리스트 어댑터가 리스트의 차이 (각 항목이 다른 아이템인지 내용이 다른 아이템인지)
         * 를 계산 해주기 위한 DiffUtil.ItemCallBack
         * 교체가 필요한 아이템에 대해서 부분적으로 데이터를 교체해준다.
         */
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder.create(
            parent,
            favClickListener
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        getItem(position)?.let { trackItem ->
            holder.bind(trackItem)
        }
    }
}