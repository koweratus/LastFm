package com.example.songkickprojektanz.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.songkickprojektanz.model.Artist
import com.example.songkickprojektanz.remote.LastFmApi
import retrofit2.HttpException
import java.io.IOException

class TopArtistSource (private val api: LastFmApi) :
    PagingSource<Int, Artist>() {
    override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        return try {
            val nextPage = params.key ?: 1
            val topArtists = api.getTopArtists(page = nextPage, )
            LoadResult.Page(
                data = topArtists.topArtists.artist,
                prevKey = if (nextPage == 1) null else nextPage - 1, nextKey = if (topArtists.topArtists.artist.isEmpty()) null else topArtists.topArtists.attributes.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
