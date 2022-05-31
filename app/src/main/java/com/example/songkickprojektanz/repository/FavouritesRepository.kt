package com.example.songkickprojektanz.repository

import com.example.songkickprojektanz.data.local.ArtistsTopAlbum
import com.example.songkickprojektanz.data.local.FavouriteArtist
import com.example.songkickprojektanz.data.local.FavouriteTopAlbum
import com.example.songkickprojektanz.data.local.LastfmDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouritesRepository @Inject constructor(private val dao: LastfmDao){

    suspend fun insertArtist(favorite: FavouriteArtist) = dao.insertArtist(favorite)

    suspend fun insertTopAlbum(topAlbum: FavouriteTopAlbum) = dao.insertTopAlbum(topAlbum)

    fun getFavorites(): Flow<List<FavouriteArtist>> = dao.getAllFavourites()

    fun getFavoritesTopAlbum(): Flow<List<FavouriteTopAlbum>> = dao.getFavoritesTopAlbum()

    fun isArtistFavourite(id: String): Flow<Boolean> = dao.isArtistFavourite(id)

    fun isAlbumFavourite(id: String): Flow<Boolean> = dao.isAlbumFavourite(id)

    suspend fun deleteOneArtist(id: String) = dao.deleteArtist(id)

    suspend fun deleteTopAlbum(id: String) = dao.deleteTopAlbum(id)

    fun getArtistsTopAlbums(id: String): Flow<ArtistsTopAlbum> =
        dao.getArtistsTopAlbums(id)
}
