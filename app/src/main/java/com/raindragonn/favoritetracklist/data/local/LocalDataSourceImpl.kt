package com.raindragonn.favoritetracklist.data.local

import com.raindragonn.favoritetracklist.data.local.room.FavoriteDao
import com.raindragonn.favoritetracklist.data.model.TrackItem

class LocalDataSourceImpl(
    private val dao: FavoriteDao
) : LocalDataSource {
    override suspend fun getFavoriteList(): List<TrackItem> =
        dao.getFavoriteList()

    override suspend fun insertFavorite(favoriteEntity: TrackItem) =
        dao.insertFavorite(favoriteEntity)

    override suspend fun getFavorite(id: Int): TrackItem? =
        dao.getFavorite(id)

    override suspend fun updateFavorite(favoriteEntity: TrackItem) =
        dao.updateFavorite(favoriteEntity)
}