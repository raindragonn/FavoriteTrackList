package com.raindragonn.favoritetracklist.data.model

data class TrackItem(
    val trackId: Int,
    val trackName: String? = null,
    val artistName: String? = null,
    val albumArtUrl: String? = null,
    val collectionName: String? = null,
    var isFavorite: Boolean = false
)
