package com.example.songkickprojektanz.screens.trackDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.responses.TrackInfoResponse
import com.example.songkickprojektanz.widgets.ErrorDisplayingResultsImage
import com.example.songkickprojektanz.widgets.ImageItem
import com.example.songkickprojektanz.widgets.Overview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrackInfoScreen(
    artistName: String,
    trackName: String
) {
    val viewModel: TrackInfoViewModel = hiltViewModel()
    val info = produceState<Resource<TrackInfoResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.initTrackInfo(artistName = artistName, trackName = trackName)
    }.value
    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            if (info is Resource.Success) {
                // get ytVideo id and display it
                //ako ima ytId, a nema ostalo prikazi ga s error screenom
                // ako ima ytId i overview prikazi to
                // ako nema ytId, a ima ostalo prikazi to
                // ako nema ytId, a nema ostalo prikazi error screen
                val youtubeId = playTrackOnYoutube(url = info.data!!.track.url)
                if (youtubeId.isNotBlank() && (info.data.track.wiki == null || info.data.track.album.image == null)) {
                    AndroidView(factory = {
                        YouTubePlayerView(context).apply {
                            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    youTubePlayer.loadVideo(youtubeId, 0f)
                                }
                            })
                        }
                    }, modifier = Modifier.fillMaxSize(1f))
                    ErrorDisplayingResultsImage()
                } else if (youtubeId.isNotBlank() && info.data.track.wiki != null) {
                    AndroidView(factory = {
                        YouTubePlayerView(context).apply {
                            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    youTubePlayer.loadVideo(youtubeId, 0f)
                                }
                            })
                        }
                    }, modifier = Modifier.fillMaxSize(1f))
                    Overview(overview = info.data.track.wiki.summary)
                    ErrorDisplayingResultsImage()
                } else if (youtubeId.isBlank() && info.data.track.wiki != null && info.data.track.album.image != null) {
                    ImageItem(
                        albumCoverArt = info.data.track?.album?.image?.get(2)?.photoUrl
                            ?: "https://bobjames.com/wp-content/themes/soundcheck/images/default-album-artwork.png",
                        albumName = info.data.track.name,
                        artistName = info.data.track.wiki?.published ?: "Unknown",
                    )
                    Overview(overview = info.data.track.wiki?.summary ?: "")
                } else {
                    ErrorDisplayingResultsImage()
                }
            }
        }
    }
}

@Composable
fun playTrackOnYoutube(url: String): String {
    var element: String by rememberSaveable { mutableStateOf("") }
    val composableScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit, block = {
        composableScope.launch {
            withContext(Dispatchers.IO) {
                val document = Jsoup.connect(url).get()
                element = document.getElementsByAttribute("data-youtube-id").attr("data-youtube-id")
            }
        }
    })
    return element
}
