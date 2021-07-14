package com.raindragonn.favoritetracklist.data.repository

import com.raindragonn.favoritetracklist.data.local.LocalDataSource
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.remote.RemoteDataSource
import com.raindragonn.favoritetracklist.data.remote.response.mapToItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrackRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : TrackRepository {
    override suspend fun getTrackList(limit: Int, offset: Int): List<TrackItem> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getTrackItemList(limit, offset).map { it.mapToItem() }
        }

    override suspend fun getFavoriteList(): List<TrackItem> = withContext(Dispatchers.IO) {
        localDataSource.getFavoriteList()
    }

    override suspend fun insertFavorite(favoriteEntity: TrackItem) =
        withContext(Dispatchers.IO) {
            localDataSource.insertFavorite(favoriteEntity)
        }

    override suspend fun getFavorite(id: Int): TrackItem? =
        withContext(Dispatchers.IO) {
            localDataSource.getFavorite(id)
        }

    override suspend fun updateFavorite(favoriteEntity: TrackItem) =
        withContext(Dispatchers.IO) {
            localDataSource.updateFavorite(favoriteEntity)
        }
}