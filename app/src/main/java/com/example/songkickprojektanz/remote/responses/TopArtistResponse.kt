package com.example.songkickprojektanz.remote.responses

import com.example.songkickprojektanz.model.Artist
import com.example.songkickprojektanz.model.Artists
import com.example.songkickprojektanz.model.Attributes
import com.google.gson.annotations.SerializedName

data class TopArtistResponse(

    @SerializedName("artists")
    val topArtists: Artists

)
