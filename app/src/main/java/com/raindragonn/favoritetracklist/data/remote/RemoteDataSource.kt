package com.raindragonn.favoritetracklist.data.remote

import com.raindragonn.favoritetracklist.data.model.TrackItem

interface RemoteDataSource {
    suspend fun getTrackItemListForItunes(
        limit: Int,
        offset: Int
    ): List<TrackItem>
}