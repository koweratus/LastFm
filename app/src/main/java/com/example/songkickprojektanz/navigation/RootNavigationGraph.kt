package com.example.songkickprojektanz.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.songkickprojektanz.MainScreen
import com.example.songkickprojektanz.screens.albumDetails.AlbumInfoScreen
import com.example.songkickprojektanz.screens.search.SearchScreen
import com.example.songkickprojektanz.screens.splash.SplashScreen
import com.example.songkickprojektanz.screens.trackDetails.TrackInfoScreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RootNavigationGraph(
    rootNavHostController: NavHostController
) {

    NavHost(rootNavHostController, startDestination = RootScreen.Splash.route) {
        composable(RootScreen.Main.route) {
            MainScreen(rootNavHostController)
        }
        composable(RootScreen.Splash.route) {
            SplashScreen(rootNavHostController)
        }
        composable(
            route = RootScreen.AlbumInfo.route + "/{${RootScreen.AlbumInfo.ARTIST_NAME}}" + "/{${RootScreen.AlbumInfo.ALBUM_NAME}}",
            arguments = listOf(navArgument(RootScreen.AlbumInfo.ARTIST_NAME) {
                type = NavType.StringType
            }, navArgument(RootScreen.AlbumInfo.ALBUM_NAME) {
                type = NavType.StringType
            })
        ) { entry ->
            AlbumInfoScreen(
                navController = rootNavHostController,
                artistName = entry.arguments?.getString(RootScreen.AlbumInfo.ARTIST_NAME)
                    .toString(),
                albumName = entry.arguments?.getString(RootScreen.AlbumInfo.ALBUM_NAME).toString()
            )
        }
        composable(
            route = RootScreen.TrackInfo.route + "/{${RootScreen.TrackInfo.ARTIST_NAME}}" + "/{${RootScreen.TrackInfo.TRACK_NAME}}",
            arguments = listOf(navArgument(RootScreen.TrackInfo.ARTIST_NAME) {
                type = NavType.StringType
            }, navArgument(RootScreen.TrackInfo.TRACK_NAME) {
                type = NavType.StringType
            })
        ) { entry ->
            TrackInfoScreen(
                artistName = entry.arguments?.getString(RootScreen.TrackInfo.ARTIST_NAME)
                    .toString(),
                trackName = entry.arguments?.getString(RootScreen.TrackInfo.TRACK_NAME).toString()
            )
        }
        composable(
            route = RootScreen.Search.route
        ) {
            SearchScreen(navController = rootNavHostController)
        }
    }
}
