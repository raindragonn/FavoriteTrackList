package com.raindragonn.favoritetracklist.data.local

import androidx.lifecycle.LiveData
import com.raindragonn.favoritetracklist.data.model.TrackItem


interface LocalDataSource {
    fun getAllFavoriteLiveList(): LiveData<List<TrackItem>>
    suspend fun insertFavorite(favoriteEntity: TrackItem)
    suspend fun getFavoriteById(id: Int): TrackItem?
    suspend fun deleteFavorite(id: Int)
}
