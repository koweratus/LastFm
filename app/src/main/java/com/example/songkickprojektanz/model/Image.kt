package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val photoUrl: String ,
    @SerializedName("size")
    val size: String
)
