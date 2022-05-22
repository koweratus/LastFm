package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class SearchQuery(
    @SerializedName("#text")
    val text: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("searchTerms")
    val searchTerms: String,
    @SerializedName("startPage")
    val startPage: Int
)
