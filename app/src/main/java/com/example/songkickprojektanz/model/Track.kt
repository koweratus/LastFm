package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("name")
    val name: String,
    @SerializedName("duration")
    val duration: Int

)
