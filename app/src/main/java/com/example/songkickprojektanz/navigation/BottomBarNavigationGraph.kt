package com.example.songkickprojektanz.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.songkickprojektanz.screens.favourites.FavouriteScreen
import com.example.songkickprojektanz.screens.home.HomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalPagerApi::class, ExperimentalCoroutinesApi::class
)
@Composable
fun BottomBarNavigationGraph(
    rootNavHostController: NavHostController,
    bottomBarNavHostController: NavHostController
) {
    NavHost(bottomBarNavHostController, startDestination = BottomBarScreen.Home.route) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(rootNavHostController)
        }
        composable(BottomBarScreen.Favourites.route) {
            FavouriteScreen()
        }

    }
}
