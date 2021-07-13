package com.raindragonn.favoritetracklist.data.local

import com.raindragonn.favoritetracklist.data.local.room.FavoriteDao
import com.raindragonn.favoritetracklist.data.local.room.entity.FavoriteEntity

class LocalDataSourceImpl(
    private val dao: FavoriteDao
) : LocalDataSource {
    override suspend fun getFavoriteList(): List<FavoriteEntity> =
        dao.getFavoriteList()

    override suspend fun insertFavorite(favoriteEntity: FavoriteEntity) =
        dao.insertFavorite(favoriteEntity)

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) =
        dao.deleteFavorite(favoriteEntity)
}