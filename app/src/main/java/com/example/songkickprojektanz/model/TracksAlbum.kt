package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class TracksAlbum(
    @SerializedName("artist")
    val artist: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: List<Image>?,
    @SerializedName("url")
    val url: String,
    @SerializedName("@attr")
    val attr: Attr
)
