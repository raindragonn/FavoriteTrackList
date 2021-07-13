package com.raindragonn.favoritetracklist.data.remote.api

import com.raindragonn.favoritetracklist.data.remote.response.TrackResponse
import retrofit2.http.GET

interface ItunesApi {
    @GET(
        "search?" +
                "term=greenday&" +
                "entity=song" +
                "&limit=100"
    )
    suspend fun getItunesTrackList(): TrackResponse
}