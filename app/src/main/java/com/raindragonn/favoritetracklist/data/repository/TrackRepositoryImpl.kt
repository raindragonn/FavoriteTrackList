package com.raindragonn.favoritetracklist.data.repository

import com.raindragonn.favoritetracklist.data.local.LocalDataSource
import com.raindragonn.favoritetracklist.data.local.room.entity.FavoriteEntity
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.remote.RemoteDataSource
import com.raindragonn.favoritetracklist.data.remote.response.mapToItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrackRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : TrackRepository {
    override suspend fun getTrackList(): List<TrackItem> = withContext(Dispatchers.IO) {
        remoteDataSource.getTrackItemList().map { it.mapToItem() }
    }

    override suspend fun getFavoriteList(): List<FavoriteEntity> = withContext(Dispatchers.IO) {
        localDataSource.getFavoriteList()
    }

    override suspend fun insertFavorite(favoriteEntity: FavoriteEntity) =
        withContext(Dispatchers.IO) {
            localDataSource.insertFavorite(favoriteEntity)
        }

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) =
        withContext(Dispatchers.IO) {
            localDataSource.deleteFavorite(favoriteEntity)
        }
}