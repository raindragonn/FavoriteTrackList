package com.raindragonn.favoritetracklist.data.local.room

import androidx.room.*
import com.raindragonn.favoritetracklist.data.model.TrackItem


@Dao
interface FavoriteDao {

    @Query("select * from favorites")
    suspend fun getFavoriteList(): List<TrackItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: TrackItem)

    @Query("select * from favorites where trackId == :id")
    suspend fun getFavorite(id: Int): TrackItem?

    @Update
    suspend fun updateFavorite(favoriteEntity: TrackItem)
}