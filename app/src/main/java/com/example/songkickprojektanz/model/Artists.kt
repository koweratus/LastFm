package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class Artists(
    @SerializedName("artist")
    val artist: List<Artist>,
    @SerializedName("@attr")
    val attributes: Attributes,
)
