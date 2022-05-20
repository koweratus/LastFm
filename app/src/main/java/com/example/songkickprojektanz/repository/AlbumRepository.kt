package com.example.songkickprojektanz.repository

import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.LastFmApi
import com.example.songkickprojektanz.remote.responses.AlbumInfoResponse
import com.example.songkickprojektanz.remote.responses.TopAlbumResponse
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val api: LastFmApi) {

    suspend fun getArtistsTopAlbums(artist: String): Resource<TopAlbumResponse> {
        val response = try {
            api.getArtistsTopAlbums(artist = artist)
        } catch (e: Exception) {

            return Resource.Error("Unknown error occurred")
        }
        return Resource.Success(response)
    }

    suspend fun getAlbumInfo(artistName: String, albumName: String): Resource<AlbumInfoResponse> {
        val response = try {
            api.getAlbumInfo(artist = artistName, album = albumName)
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        return Resource.Success(response)
    }
}
