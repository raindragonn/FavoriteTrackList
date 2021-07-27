package com.raindragonn.favoritetracklist.data.local

import androidx.lifecycle.LiveData
import com.raindragonn.favoritetracklist.data.local.room.FavoriteDao
import com.raindragonn.favoritetracklist.data.model.TrackItem

class LocalDataSourceImpl(
    private val dao: FavoriteDao
) : LocalDataSource {
    override fun getAllFavoriteLiveList(): LiveData<List<TrackItem>> =
        dao.getAllFavoriteLiveList()

    override suspend fun insertFavorite(favoriteEntity: TrackItem) =
        dao.insertFavorite(favoriteEntity)

    override suspend fun getFavoriteById(id: Int): TrackItem? =
        dao.getFavoriteById(id)

    override suspend fun deleteFavorite(id: Int) =
        dao.deleteFavorite(id)
}