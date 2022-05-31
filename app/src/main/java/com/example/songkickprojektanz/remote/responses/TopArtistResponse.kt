package com.example.songkickprojektanz.remote.responses

import com.example.songkickprojektanz.model.Artists
import com.google.gson.annotations.SerializedName

data class TopArtistResponse(
    @SerializedName("artists")
    val topArtists: Artists
)
