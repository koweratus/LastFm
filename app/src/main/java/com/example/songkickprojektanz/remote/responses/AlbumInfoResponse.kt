package com.example.songkickprojektanz.remote.responses

import com.example.songkickprojektanz.model.AlbumInfo
import com.google.gson.annotations.SerializedName

data class AlbumInfoResponse(
    @SerializedName("album")
    val albumInfo: AlbumInfo
)
