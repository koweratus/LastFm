package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class TrackInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("mbid")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("album")
    val album: AlbumInfo,
    @SerializedName("wiki")
    val wiki: Wiki?,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("playcount")
    val playcount: String,
)
