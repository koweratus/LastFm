package com.example.songkickprojektanz.remote

import com.example.songkickprojektanz.BuildConfig.API_KEY
import com.example.songkickprojektanz.remote.responses.*
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApi {

    @GET("?method=chart.gettopartists")
    suspend fun getTopArtists(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json",
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50
    ): TopArtistResponse

    @GET("?method=artist.gettopalbums")
    suspend fun getArtistsTopAlbums(
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json"
    ): TopAlbumResponse

    @GET("?method=album.getinfo")
    suspend fun getAlbumInfo(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("artist") artist: String,
        @Query("album") album: String,
        @Query("format") format: String = "json"
    ): AlbumInfoResponse

    @GET("?method=track.getinfo")
    suspend fun getTrackInfo(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("artist") artist: String,
        @Query("track") track: String,
        @Query("format") format: String = "json"
    ): TrackInfoResponse

    @GET("?method=artist.search")
    suspend fun getSearchResults(
        @Query("artist") artist: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("format") format: String = "json"
    ): SearchResponse
}
