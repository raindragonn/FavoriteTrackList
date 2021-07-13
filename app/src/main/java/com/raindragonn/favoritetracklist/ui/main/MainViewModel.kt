package com.raindragonn.favoritetracklist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.raindragonn.favoritetracklist.data.local.room.entity.FavoriteEntity
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.repository.TrackRepository
import com.raindragonn.favoritetracklist.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: TrackRepository
) : BaseViewModel() {

    private val _trackList = MutableLiveData<List<TrackItem>>()
    val trackList: LiveData<List<TrackItem>>
        get() = _trackList

    private val _favoriteList = MutableLiveData<List<TrackItem>>()
    val favoriteList: LiveData<List<TrackItem>>
        get() = _favoriteList

    fun getList() = viewModelScope.launch {
        startLoading()
        setMatchingTrackList()
        stopLoading()
    }

    private suspend fun setMatchingTrackList() {
        var nTrackList = repository.getTrackList()
        repository.getFavoriteList().forEach { favorite ->
            nTrackList = nTrackList.map {
                if (it.trackId == favorite.trackId) {
                    it.copy(isFavorite = true)
                } else
                    it
            }
        }
        val nFavoriteList = nTrackList.filter { it.isFavorite }

        _favoriteList.value = nFavoriteList
        _trackList.value = nTrackList
    }

    private fun insertFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        repository.insertFavorite(favoriteEntity)
        setMatchingTrackList()
    }

    private fun deleteFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        repository.deleteFavorite(favoriteEntity)
        setMatchingTrackList()
    }

    val onFavoriteClick: (trackItem: TrackItem) -> Unit = {
        if (it.trackId != -1) {
            val favoriteEntity = FavoriteEntity(it.trackId)
            when (it.isFavorite) {
                true -> deleteFavorite(favoriteEntity)
                false -> insertFavorite(favoriteEntity)
            }
        }
    }
}