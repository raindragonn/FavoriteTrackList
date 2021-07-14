package com.raindragonn.favoritetracklist.data.remote

import com.raindragonn.favoritetracklist.data.remote.response.Track
import retrofit2.http.Query

interface RemoteDataSource {
    suspend fun getTrackItemList(
        limit: Int,
        offset: Int
    ): List<Track>
}