package com.example.songkickprojektanz.navigation

sealed class RootScreen(
    val route: String,
    val title: String
) {
    object Main : RootScreen(
        route = "main",
        title = "Main"
    )

    object AlbumInfo : RootScreen(
        route = "album_info_screen",
        title = "Album Info"
    ) {
        const val ARTIST_NAME = "artistName"
        const val ALBUM_NAME = "albumName"
    }

    object TrackInfo : RootScreen(
        route = "track_info_screen",
        title = "Track Info"
    ) {
        const val ARTIST_NAME = "artistName"
        const val TRACK_NAME = "trackName"
    }

    object Search : RootScreen(
        route = "search",
        title = "Search"
    )
}
