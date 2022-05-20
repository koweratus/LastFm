package com.example.songkickprojektanz.screens.trackDetails

import androidx.lifecycle.ViewModel
import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.responses.AlbumInfoResponse
import com.example.songkickprojektanz.remote.responses.TrackInfoResponse
import com.example.songkickprojektanz.repository.AlbumRepository
import com.example.songkickprojektanz.repository.TrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackInfoViewModel@Inject constructor(
    private val trackRepository: TrackRepository,
) : ViewModel() {

    suspend fun initTrackInfo(artistName: String, trackName: String): Resource<TrackInfoResponse> {
        return trackRepository.getTrackInfo(artistName,trackName)
    }
}
