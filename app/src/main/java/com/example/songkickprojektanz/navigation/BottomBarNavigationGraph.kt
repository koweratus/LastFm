package com.example.songkickprojektanz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.songkickprojektanz.screens.favourites.FavouriteScreen
import com.example.songkickprojektanz.screens.home.HomeScreen


@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class,
    com.google.accompanist.pager.ExperimentalPagerApi::class
)
@Composable
fun BottomBarNavigationGraph(
    rootNavHostController: NavHostController,
    bottomBarNavHostController: NavHostController
) {
    NavHost(bottomBarNavHostController, startDestination = BottomBarScreen.Home.route) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(BottomBarScreen.Favourites.route) {
            FavouriteScreen()
        }

    }
}