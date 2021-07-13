package com.raindragonn.favoritetracklist.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.raindragonn.favoritetracklist.data.local.room.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("select * from favorites")
    suspend fun getFavoriteList(): List<FavoriteEntity>

    @Insert
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
}