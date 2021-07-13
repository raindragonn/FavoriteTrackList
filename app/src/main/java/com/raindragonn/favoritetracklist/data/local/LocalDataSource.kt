package com.raindragonn.favoritetracklist.data.local

import com.raindragonn.favoritetracklist.data.local.room.entity.FavoriteEntity

interface LocalDataSource {
    suspend fun getFavoriteList(): List<FavoriteEntity>
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
}