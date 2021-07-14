package com.raindragonn.favoritetracklist.data.repository

import com.raindragonn.favoritetracklist.data.model.TrackItem

interface TrackRepository {
    suspend fun getTrackList(limit: Int, offset: Int): List<TrackItem>
    suspend fun getFavoriteList(): List<TrackItem>
    suspend fun getFavorite(id: Int): TrackItem?
    suspend fun insertFavorite(favoriteEntity: TrackItem)
    suspend fun updateFavorite(favoriteEntity: TrackItem)
}