package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class TopAlbum(
    @SerializedName("name")
    val name: String,
    @SerializedName("playcount")
    val playcount: Int,
    @SerializedName("mbid")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("image")
    val image: List<Image>,
)
