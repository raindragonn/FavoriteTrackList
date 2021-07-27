package com.raindragonn.favoritetracklist.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.raindragonn.favoritetracklist.data.local.LocalDataSource
import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.pagingsoruce.ItunesPagingSource
import com.raindragonn.favoritetracklist.data.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrackRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : TrackRepository {
    companion object {
        private const val REQUEST_LIMIT_DATA = 50
    }

    override fun getTrackList(viewModelScope: CoroutineScope): LiveData<PagingData<TrackItem>> {
        return Pager(
            /**
             * PagingConfig = 로드 대기시간 초기 로드 크기 등 설정
             * 초기 로드 크기는 pageSize * 3
             */
            config = PagingConfig(
                pageSize = REQUEST_LIMIT_DATA
            ),
            pagingSourceFactory = {
                ItunesPagingSource(
                    remoteDataSource
                )
            }
        ).liveData
            .cachedIn(viewModelScope)
    }

    override fun getAllFavoriteLiveList(): LiveData<List<TrackItem>> =
        localDataSource.getAllFavoriteLiveList()

    override suspend fun insertFavorite(favoriteEntity: TrackItem) =
        withContext(Dispatchers.IO) {
            localDataSource.insertFavorite(favoriteEntity)
        }

    override suspend fun getFavoriteById(id: Int): TrackItem? =
        withContext(Dispatchers.IO) {
            localDataSource.getFavoriteById(id)
        }

    override suspend fun deleteFavorite(id: Int) =
        withContext(Dispatchers.IO) {
            localDataSource.deleteFavorite(id)
        }
}