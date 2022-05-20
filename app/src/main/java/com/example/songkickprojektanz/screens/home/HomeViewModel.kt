package com.example.songkickprojektanz.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.songkickprojektanz.model.Artist
import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.responses.TopAlbumResponse
import com.example.songkickprojektanz.repository.AlbumRepository
import com.example.songkickprojektanz.repository.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val artistsRepository: ArtistRepository,
    private val albumsRepository: AlbumRepository,
) : ViewModel() {
    private val _expandedCardIdsList = MutableStateFlow(listOf<Int>())
    val expandedCardIdsList: StateFlow<List<Int>> get() = _expandedCardIdsList

    fun onCardArrowClicked(cardId: Int) {
        _expandedCardIdsList.value = _expandedCardIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }

    private var _topArtists: MutableStateFlow<PagingData<Artist>> =
        MutableStateFlow(PagingData.empty())
    val topArtists: Flow<PagingData<Artist>> = _topArtists

    init {
        initTopArtists()
    }

    private fun initTopArtists() {
        viewModelScope.launch {
            _topArtists.value =
                artistsRepository.getTopArtists().cachedIn(viewModelScope).first()
        }
    }

    suspend fun initArtistsTopAlbums(artist: String): Resource<TopAlbumResponse> {
        return albumsRepository.getArtistsTopAlbums(artist)
    }
}
