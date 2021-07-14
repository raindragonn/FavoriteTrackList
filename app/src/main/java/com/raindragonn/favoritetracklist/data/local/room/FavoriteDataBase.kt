package com.raindragonn.favoritetracklist.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raindragonn.favoritetracklist.data.model.TrackItem

@Database(
    entities = [TrackItem::class],
    version = 1
)
abstract class FavoriteDataBase : RoomDatabase() {
    abstract val dao: FavoriteDao
}