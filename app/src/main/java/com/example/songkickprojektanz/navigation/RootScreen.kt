package com.example.songkickprojektanz.navigation

sealed class RootScreen(
    val route: String,
    val title: String
) {
    object Main : RootScreen(
        route = "main",
        title = "Main"
    )

    object ConcertDetails : RootScreen(
        route = "concert_details_screen",
        title = "Concert Details"
    ) {

        const val ARGUMENT_ID = "id"
    }
}