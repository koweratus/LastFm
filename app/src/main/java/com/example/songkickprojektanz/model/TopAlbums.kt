package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class TopAlbums(
    @SerializedName("album")
    val album: List<TopAlbum>,
    @SerializedName("@attr")
    val attributes: Attributes,
)
