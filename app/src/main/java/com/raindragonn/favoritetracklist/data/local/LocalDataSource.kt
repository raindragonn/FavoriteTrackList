package com.raindragonn.favoritetracklist.data.local

import com.raindragonn.favoritetracklist.data.model.TrackItem


interface LocalDataSource {
    suspend fun getFavoriteList(): List<TrackItem>
    suspend fun insertFavorite(favoriteEntity: TrackItem)
    suspend fun updateFavorite(favoriteEntity: TrackItem)
    suspend fun getFavorite(id: Int): TrackItem?
}
