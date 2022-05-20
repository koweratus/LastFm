package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class AlbumInfo(
    @SerializedName("artist")
    val artist: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("tags")
    val tags: Tags?,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: List<Image>?,
    @SerializedName("tracks")
    val tracks: Tracks?,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("playcount")
    val playcount: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("wiki")
    val wiki: Wiki?
)
