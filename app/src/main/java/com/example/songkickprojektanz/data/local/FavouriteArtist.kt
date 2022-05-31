package com.example.songkickprojektanz.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.songkickprojektanz.Constants.ARTIST_TABLE
import com.example.songkickprojektanz.model.Image

@Entity(tableName = ARTIST_TABLE)
data class FavouriteArtist(
    @PrimaryKey val id: String,
    val favourite: Boolean,
    val name: String,
    val listeners: String,
    val playCount: String,
    val url: String,
    val streamable: String,
    //val image: List<ImageLocal>
)
