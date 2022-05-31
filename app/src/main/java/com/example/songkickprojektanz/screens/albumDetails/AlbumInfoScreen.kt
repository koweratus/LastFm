package com.example.songkickprojektanz.screens.albumDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.responses.AlbumInfoResponse
import com.example.songkickprojektanz.widgets.ImageItem
import com.example.songkickprojektanz.widgets.ImageItemShimmer
import com.example.songkickprojektanz.widgets.Overview
import com.example.songkickprojektanz.widgets.TopBilledCastSectionItem
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AlbumInfoScreen(
    artistName: String,
    albumName: String,
    navController: NavController
) {
    val viewModel: AlbumInfoViewModel = hiltViewModel()
    val info = produceState<Resource<AlbumInfoResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.initAlbumInfo(artistName = artistName, albumName = albumName)
    }.value

    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            when(info){
                is Resource.Loading -> ImageItemShimmer()
                is Resource.Success-> ImageItem(
                    albumCoverArt = info.data?.albumInfo?.image!![5].photoUrl,
                    albumName = info.data.albumInfo.name,
                    artistName = info.data.albumInfo.artist
                )
            }
            Overview(overview = info.data?.albumInfo?.wiki?.summary.toString())
            TopBilledCastSectionItem(info.data, navController = navController, artistName)
        }
    }
}
