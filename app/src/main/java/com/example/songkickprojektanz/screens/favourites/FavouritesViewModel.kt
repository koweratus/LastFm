package com.example.songkickprojektanz.screens.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.songkickprojektanz.data.local.ArtistsTopAlbum
import com.example.songkickprojektanz.data.local.FavouriteArtist
import com.example.songkickprojektanz.data.local.FavouriteTopAlbum
import com.example.songkickprojektanz.repository.FavouritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val repository: FavouritesRepository) :
    ViewModel() {

    val favourites = repository.getFavorites()

    val favouritesTopAlbum = repository.getFavoritesTopAlbum()

    fun insertFavorite(favorite: FavouriteArtist) {
        viewModelScope.launch {
            repository.insertArtist(favorite)
        }
    }

    fun insertTopAlbum(topAlbum: FavouriteTopAlbum) {
        viewModelScope.launch {
            repository.insertTopAlbum(topAlbum)
        }
    }

    fun isArtistFavourite(id: String): Flow<Boolean> {
        return repository.isArtistFavourite(id)
    }

    fun isAlbumFavourite(id: String): Flow<Boolean> {
        return repository.isAlbumFavourite(id)
    }

    fun deleteOneArtist(id: String) {
        viewModelScope.launch {
            repository.deleteOneArtist(id)
        }
    }

    fun deleteTopAlbum(id: String) {
        viewModelScope.launch {
            repository.deleteTopAlbum(id)
        }
    }

    fun getArtistsTopAlbums(id: String): Flow<ArtistsTopAlbum> {
        return repository.getArtistsTopAlbums(id)
    }
}
