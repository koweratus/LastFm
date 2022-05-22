package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class Attr(
    @SerializedName("position")
    val position: String,
    @SerializedName("for")
    val searchFor: String
)
