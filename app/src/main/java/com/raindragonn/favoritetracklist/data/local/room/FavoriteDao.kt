package com.raindragonn.favoritetracklist.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raindragonn.favoritetracklist.data.model.TrackItem


@Dao
interface FavoriteDao {

    @Query("select * from favorites")
    fun getAllFavoriteLiveList(): LiveData<List<TrackItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: TrackItem)

    @Query("select * from favorites where trackId == :id")
    suspend fun getFavoriteById(id: Int): TrackItem?

    @Query("delete from favorites where trackId == :id")
    suspend fun deleteFavorite(id: Int)
}