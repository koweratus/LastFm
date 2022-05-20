package com.example.songkickprojektanz.remote.responses

import com.example.songkickprojektanz.model.TrackInfo
import com.google.gson.annotations.SerializedName

data class TrackInfoResponse(
    @SerializedName("track")
    val track : TrackInfo
)
