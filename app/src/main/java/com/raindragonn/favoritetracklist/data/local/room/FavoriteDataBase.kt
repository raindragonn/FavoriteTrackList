package com.raindragonn.favoritetracklist.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raindragonn.favoritetracklist.data.local.room.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1
)
abstract class FavoriteDataBase : RoomDatabase() {
    abstract val dao: FavoriteDao
}