package com.raindragonn.favoritetracklist.data.remote

import com.raindragonn.favoritetracklist.data.remote.api.ItunesApi
import com.raindragonn.favoritetracklist.data.remote.response.Track

class RemoteDataSourceImpl(
    private val api: ItunesApi
) : RemoteDataSource {
    override suspend fun getTrackItemList(): List<Track> {
        return api.getItunesTrackList().results ?: emptyList()
    }
}