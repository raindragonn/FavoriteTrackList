package com.raindragonn.favoritetracklist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.repository.TrackRepository
import com.raindragonn.favoritetracklist.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: TrackRepository
) : BaseViewModel() {

    // 노래 리스트 LiveData
    private val _trackList = MutableLiveData<List<TrackItem>>()
    val trackList: LiveData<List<TrackItem>>
        get() = _trackList

    // 좋아요 리스트 LiveData
    private val _favoriteList = MutableLiveData<List<TrackItem>>()
    val favoriteList: LiveData<List<TrackItem>>
        get() = _favoriteList

    // 검색 시 요청 데이터 갯수
    private val limit = 50

    // 검색 시 데이터 시작점
    private var offset = 0

    // 리스트 불러오기
    fun loadList() = viewModelScope.launch {
        kotlin.runCatching {
            startLoading()
            val list = (_trackList.value ?: getLoadMoreItem()).toMutableList()
            matching(list)
            stopLoading()
        }.onSuccess {
            stopError()
        }.onFailure {
            startError()
        }
    }

    // 노래 리스트 와 좋아요 리스트를 비교하고 좋아요를 맞춰줍니다.
    private suspend fun matching(list: List<TrackItem>) {
        var trackList: List<TrackItem> = list
        val favList = repository.getFavoriteList()

        favList.forEach { fav ->
            trackList = trackList.map { track ->
                if (track.trackId == fav.trackId) {
                    track.copy(isFavorite = fav.isFavorite)
                } else {
                    track
                }
            }.toMutableList()
        }

        _favoriteList.value = favList.filter { it.isFavorite }
        _trackList.value = trackList
    }

    // 추가 노래 리스트 불러오기
    private suspend fun loadMoreList() {
        kotlin.runCatching {
            val list = (_trackList.value ?: getLoadMoreItem()).toMutableList()
            list.addAll(getLoadMoreItem())
            matching(list)
        }.onSuccess {
            stopError()
        }.onFailure {
            startError()
        }
    }

    // API를 통해 불러온 TrackList
    private suspend fun getLoadMoreItem() =
        repository.getTrackList(limit, offset).apply {
            offset += (limit + 1)
        }

    // DB에 해당 노래정보를 변경
    private suspend fun setFavorite(trackItem: TrackItem) {
        if (hasData(trackItem)) {
            updateFavorite(trackItem)
        } else {
            insertFavorite(trackItem)
        }
        loadList()
    }

    // DB에 해당 좋아요 정보가 있는지 확인
    private suspend fun hasData(trackItem: TrackItem): Boolean =
        repository.getFavorite(trackItem.trackId) != null

    // DB에 해당 좋아요 정보 업데이트
    private suspend fun updateFavorite(entity: TrackItem) {
        repository.updateFavorite(entity)
    }

    // DB에 해당 좋아요 정보 삽입
    private suspend fun insertFavorite(entity: TrackItem) {
        repository.insertFavorite(entity)
    }

    // BindingAdapter 를 통해 넘길 좋아요 클릭 리스너
    val onFavoriteClick: (trackItem: TrackItem) -> Unit = {
        viewModelScope.launch {
            val nTrackItem = it.copy(isFavorite = it.isFavorite.not())
            setFavorite(nTrackItem)
        }
    }

    // BindingAdapter 를 통해 넘길 스크롤 리스너
    val onLoadMore: () -> Unit = {
        viewModelScope.launch {
            loadMoreList()
        }
    }

    // BindingAdapter 를 통해 넘길 에러 리스너
    val onRefreshClick: () -> Unit = {
        loadList()
    }
}