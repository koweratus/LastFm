package com.example.songkickprojektanz.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.songkickprojektanz.model.Artist
import com.example.songkickprojektanz.paging.TopArtistSource
import com.example.songkickprojektanz.remote.LastFmApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistRepository@Inject constructor(private val api: LastFmApi) {

    fun getTopArtists(): Flow<PagingData<Artist>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TopArtistSource(api)
            }
        ).flow
    }
}
