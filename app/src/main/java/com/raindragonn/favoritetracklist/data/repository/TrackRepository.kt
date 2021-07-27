package com.raindragonn.favoritetracklist.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.raindragonn.favoritetracklist.data.model.TrackItem
import kotlinx.coroutines.CoroutineScope

interface TrackRepository {
    fun getTrackList(viewModelScope: CoroutineScope): LiveData<PagingData<TrackItem>>
    fun getAllFavoriteLiveList(): LiveData<List<TrackItem>>
    suspend fun getFavoriteById(id: Int): TrackItem?
    suspend fun insertFavorite(favoriteEntity: TrackItem)
    suspend fun deleteFavorite(id: Int)
}