package com.example.songkickprojektanz.navigation

import com.example.songkickprojektanz.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Favourites : BottomBarScreen(
        route = "favourites",
        title = "Favourites",
        icon = R.drawable.ic_favourite
    )
}