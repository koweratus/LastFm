package com.example.songkickprojektanz.remote.responses

import com.example.songkickprojektanz.model.TopAlbums
import com.google.gson.annotations.SerializedName

data class TopAlbumResponse(
     @SerializedName("topalbums")
     val topAlbums: TopAlbums
)
