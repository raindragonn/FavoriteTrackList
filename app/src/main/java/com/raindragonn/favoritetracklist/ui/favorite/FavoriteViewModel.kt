package com.raindragonn.favoritetracklist.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.repository.TrackRepository
import com.raindragonn.favoritetracklist.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FavoriteViewModel(
    repository: TrackRepository
) : BaseViewModel(repository) {
    val favoriteList: LiveData<List<TrackItem>> = repository.getAllFavoriteLiveList()

    // BindingAdapter 를 통해 넘길 좋아요 클릭 리스너
    val onFavoriteClick: (trackItem: TrackItem) -> Unit = { track ->
        viewModelScope.launch {
            setFavorite(track)
        }
    }
}