package com.example.songkickprojektanz.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.songkickprojektanz.model.Artist
import com.example.songkickprojektanz.model.Search
import com.example.songkickprojektanz.model.SearchQuery
import com.example.songkickprojektanz.remote.LastFmApi
import retrofit2.HttpException
import java.io.IOException

class SearchSource  (private val api: LastFmApi, private val artist: String) :
    PagingSource<Int, Artist>() {
    override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        return try {
            val nextPage = params.key ?: 1
            val searchResults = api.getSearchResults(page = nextPage, artist = artist )
            LoadResult.Page(
                data = searchResults.results.artistMatches.artist,
                prevKey = if (nextPage == 1) null else nextPage - 1, nextKey = if (searchResults.results.artistMatches.artist.isEmpty()) null else searchResults.results.query.startPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
