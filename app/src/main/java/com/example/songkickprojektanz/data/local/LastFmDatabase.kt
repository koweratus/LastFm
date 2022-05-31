package com.example.songkickprojektanz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(FavouriteTopAlbum::class, FavouriteArtist::class), version = 4)

abstract class LastFmDatabase : RoomDatabase() {
    abstract fun getLastfmDao() : LastfmDao
}
