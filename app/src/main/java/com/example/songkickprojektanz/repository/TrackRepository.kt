package com.example.songkickprojektanz.repository

import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.LastFmApi
import com.example.songkickprojektanz.remote.responses.AlbumInfoResponse
import com.example.songkickprojektanz.remote.responses.TrackInfoResponse
import javax.inject.Inject

class TrackRepository @Inject constructor(private val api: LastFmApi) {

    suspend fun getTrackInfo(artistName: String, trackName: String): Resource<TrackInfoResponse> {
        val response = try {
            api.getTrackInfo(artist = artistName, track = trackName)
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        return Resource.Success(response)
    }
}
