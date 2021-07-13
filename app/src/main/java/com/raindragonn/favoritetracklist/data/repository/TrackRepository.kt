package com.raindragonn.favoritetracklist.data.repository

import com.raindragonn.favoritetracklist.data.remote.response.Track

interface TrackRepository {
    suspend fun getTrackList(): List<Track>
}