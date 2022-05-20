package com.example.songkickprojektanz.screens.albumDetails

import androidx.lifecycle.ViewModel
import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.responses.AlbumInfoResponse
import com.example.songkickprojektanz.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumInfoViewModel@Inject constructor(
    private val albumsRepository: AlbumRepository,
) : ViewModel() {

    suspend fun initAlbumInfo(artistName: String, albumName: String): Resource<AlbumInfoResponse> {
        return albumsRepository.getAlbumInfo(artistName,albumName)
    }
}
