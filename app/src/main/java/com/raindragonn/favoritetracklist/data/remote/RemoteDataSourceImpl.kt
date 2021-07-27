package com.raindragonn.favoritetracklist.data.remote

import com.raindragonn.favoritetracklist.data.model.TrackItem
import com.raindragonn.favoritetracklist.data.remote.api.ItunesApi
import com.raindragonn.favoritetracklist.data.remote.response.mapToItem

class RemoteDataSourceImpl(
    private val api: ItunesApi
) : RemoteDataSource {
    override suspend fun getTrackItemListForItunes(
        limit: Int,
        offset: Int
    ): List<TrackItem> {
        return api.getItunesTrackList(limit, offset).results?.map { it.mapToItem() } ?: emptyList()
    }
}