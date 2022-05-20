package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String
)
