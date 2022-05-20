package com.example.songkickprojektanz.screens.trackDetails

import android.os.StrictMode
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.songkickprojektanz.R
import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.responses.TrackInfoResponse
import com.example.songkickprojektanz.widgets.ImageItem
import com.example.songkickprojektanz.widgets.Overview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import okhttp3.internal.wait
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

    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        item {
            if (info is Resource.Success)
                PlayTrackOnYoutube(url = info.data?.track!!.url,info.data)
                Overview(overview = info.data?.track?.wiki?.summary ?: "Overview not available")
        }
    }
}

@Composable
fun PlayTrackOnYoutube(url: String, info: TrackInfoResponse) {
    val context = LocalContext.current
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    val composableScope = rememberCoroutineScope()

/*    LaunchedEffect(key1 = Unit, block = {
        composableScope.launch {

        }
    })*/
    val document = Jsoup.connect(url).get()
    val element = document.getElementsByAttribute("data-youtube-id").attr("data-youtube-id")
    if (element.isNotBlank()) {
        AndroidView(factory = {
            YouTubePlayerView(context).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(element, 0f)
                    }
                })
            }
        }, modifier = Modifier.fillMaxSize(1f))
    } else {
        ImageItem(
            albumCoverArt = info.track?.album?.image?.get(2)?.photoUrl ?: "https://bobjames.com/wp-content/themes/soundcheck/images/default-album-artwork.png",
            albumName = info.track.name,
            albumReleaseDate = info.track?.wiki?.published ?: "Unknown",
            listeners = info.track.listeners,
        )
    }
}

