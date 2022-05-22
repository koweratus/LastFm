package com.example.songkickprojektanz.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.songkickprojektanz.model.Artist
import com.example.songkickprojektanz.model.SearchQuery
import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.paging.SearchSource
import com.example.songkickprojektanz.paging.TopArtistSource
import com.example.songkickprojektanz.remote.LastFmApi
import com.example.songkickprojektanz.remote.responses.SearchResponse
import com.example.songkickprojektanz.remote.responses.TrackInfoResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository@Inject constructor(private val api: LastFmApi) {

    fun getSearchResults(artist: String): Flow<PagingData<Artist>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                SearchSource(api,artist)
            }
        ).flow
    }
}

