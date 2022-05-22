package com.example.songkickprojektanz.model

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("opensearch:Query")
    val query: SearchQuery,
    @SerializedName("opensearch:totalResults")
    val totalResults: Int,
    @SerializedName("opensearch:startIndex")
    val startIndex: Int,
    @SerializedName("opensearch:itemsPerPage")
    val itemsPerPage: Int,
    @SerializedName("artistmatches")
    val artistMatches: Artists,
    @SerializedName("@attr")
    val attr: Attr
)
