package com.raindragonn.favoritetracklist.data.repository

import com.raindragonn.favoritetracklist.data.local.room.entity.FavoriteEntity
import com.raindragonn.favoritetracklist.data.model.TrackItem

interface TrackRepository {
    suspend fun getTrackList(): List<TrackItem>
    suspend fun getFavoriteList(): List<FavoriteEntity>
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
}