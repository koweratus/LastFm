package com.example.songkickprojektanz.remote

import com.example.songkickprojektanz.BuildConfig.API_KEY
import com.example.songkickprojektanz.remote.responses.AlbumInfoResponse
import com.example.songkickprojektanz.remote.responses.TopAlbumResponse
import com.example.songkickprojektanz.remote.responses.TopArtistResponse
import com.example.songkickprojektanz.remote.responses.TrackInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApi {

    @GET("?method=chart.gettopartists")
    suspend fun getTopArtists(
       // @Query("country") country: String,
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
}
