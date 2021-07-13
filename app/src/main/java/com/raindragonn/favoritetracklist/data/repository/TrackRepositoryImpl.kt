package com.raindragonn.favoritetracklist.data.repository

import com.raindragonn.favoritetracklist.data.remote.api.ItunesApi
import com.raindragonn.favoritetracklist.data.remote.response.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrackRepositoryImpl(
    private val api: ItunesApi
) : TrackRepository {
    override suspend fun getTrackList(): List<Track> = withContext(Dispatchers.IO) {
        api.getItunesTrackList().results ?: emptyList()
    }
}