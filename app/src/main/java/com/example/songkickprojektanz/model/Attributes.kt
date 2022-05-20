package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("perPage")
    val perPage: Int,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("page")
    val page: Int ,
    @SerializedName("country")
    val country: String
)
