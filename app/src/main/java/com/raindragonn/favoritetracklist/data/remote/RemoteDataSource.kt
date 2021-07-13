package com.raindragonn.favoritetracklist.data.remote

import com.raindragonn.favoritetracklist.data.remote.response.Track

interface RemoteDataSource {
    suspend fun getTrackItemList(): List<Track>
}