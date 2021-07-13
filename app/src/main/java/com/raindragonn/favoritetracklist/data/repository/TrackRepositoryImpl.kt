package com.raindragonn.favoritetracklist.data.repository

import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.remote.api.ItunesApi
import com.raindragonn.favoritetracklist.data.remote.response.mapToItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrackRepositoryImpl(
    private val api: ItunesApi
) : TrackRepository {
    override suspend fun getTrackList(): List<TrackItem> = withContext(Dispatchers.IO) {
        api.getItunesTrackList().results?.map { it.mapToItem() } ?: emptyList()
    }
}