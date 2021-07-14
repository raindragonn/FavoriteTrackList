package com.raindragonn.favoritetracklist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "Favorites"
)
data class TrackItem(
    @PrimaryKey
    val trackId: Int,
    val trackName: String,
    val artistName: String,
    val albumArtUrl: String,
    val collectionName: String,
    var isFavorite: Boolean = false
)
