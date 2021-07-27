package com.raindragonn.favoritetracklist.ui.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.repository.TrackRepository
import com.raindragonn.favoritetracklist.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class TrackViewModel(
    repository: TrackRepository
) : BaseViewModel(repository) {

    // 페이징 라이브러리를 통해 가져온 PagingData
    private var _trackPagingData: MutableLiveData<PagingData<TrackItem>> =
        repository.getTrackList(viewModelScope) as MutableLiveData<PagingData<TrackItem>>

    val trackPagingData: LiveData<PagingData<TrackItem>>
        get() = _trackPagingData

    val favoriteList = repository.getAllFavoriteLiveList()


    fun matchedTrackListByFavorite(favList: List<TrackItem>) {
        val pagingData = _trackPagingData.value ?: return

        val updatePagingData = pagingData.map { trackItem ->
            val hasFavorite = favList.any { favoriteItem ->
                trackItem.trackId == favoriteItem.trackId
            }
            if (hasFavorite) {
                trackItem.collectionName
                return@map trackItem.copy(isFavorite = true)
            }
            trackItem.copy(isFavorite = false)
        }

        _trackPagingData.value = updatePagingData
    }


    // BindingAdapter 를 통해 넘길 좋아요 클릭 리스너
    val onFavoriteClick: (favTrack: TrackItem) -> Unit = { track ->
        viewModelScope.launch {
            setFavorite(track)
        }
    }
}