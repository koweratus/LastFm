package com.example.songkickprojektanz.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.songkickprojektanz.Constants
import com.example.songkickprojektanz.model.Image

@Entity(tableName = Constants.TOPALBUM_TABLE)
data class FavouriteTopAlbum(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val playcount: Int,
    val url: String,
    val favourite: Boolean,
    val image: String,
    val artistName: String
)
