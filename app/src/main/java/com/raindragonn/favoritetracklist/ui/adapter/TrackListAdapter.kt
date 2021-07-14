package com.raindragonn.favoritetracklist.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.databinding.ItemTrackBinding

class TrackListAdapter : ListAdapter<TrackItem, TrackListAdapter.ViewHolder>(differ) {
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

    // 좋아요 버튼 클릭 리스너
    var favoriteClickListener: ((TrackItem) -> Unit)? = null

    // 추가 로딩 스크롤 리스너
    var loadMoreListener: (() -> Unit)? = null

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

        if (position == currentList.size - 1) {
            Log.d("DEV_LOG", "onBindViewHolder: ")
            loadMoreListener?.invoke()
        }
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