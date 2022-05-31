package com.example.songkickprojektanz.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class ArtistsTopAlbum(
    @Embedded val favourite: FavouriteArtist,
    @Relation(
        parentColumn = "name",
        entityColumn = "artistName"
    )
    val topAlbum: List<FavouriteTopAlbum>
)
