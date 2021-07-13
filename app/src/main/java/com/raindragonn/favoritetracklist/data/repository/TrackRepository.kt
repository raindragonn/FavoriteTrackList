package com.raindragonn.favoritetracklist.data.repository

import com.raindragonn.favoritetracklist.data.model.TrackItem

interface TrackRepository {
    suspend fun getTrackList(): List<TrackItem>
}