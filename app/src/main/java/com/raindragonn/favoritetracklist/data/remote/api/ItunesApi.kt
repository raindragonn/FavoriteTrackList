package com.raindragonn.favoritetracklist.data.remote.api

import com.raindragonn.favoritetracklist.data.remote.response.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {
    /**
     * 검색어와 종류는 각각 greenday 및 song으로 고정,
     * @param offset: 가져올 데이터의 시작점
     */
    @GET(
        "search?" +
                "term=greenday&" +
                "entity=song"
    )
    suspend fun getItunesTrackList(
        @Query("limit")
        limit: Int,
        @Query("offset")
        offset: Int
    ): TrackResponse
}