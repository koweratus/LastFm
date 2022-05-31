package com.example.songkickprojektanz.remote.responses

import com.example.songkickprojektanz.model.Search
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("results")
    val results: Search
)
