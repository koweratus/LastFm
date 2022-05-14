package com.example.songkickprojektanz.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.songkickprojektanz.MainScren
import com.example.songkickprojektanz.navigation.RootScreen
import com.example.songkickprojektanz.screens.details.ConcertDetailsScreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RootNavigationGraph(
    rootNavHostController: NavHostController
) {

    NavHost(rootNavHostController, startDestination = RootScreen.Main.route) {
        composable(RootScreen.Main.route) {
            MainScren(rootNavHostController)
        }
        composable(
            route = RootScreen.ConcertDetails.route + "/{${RootScreen.ConcertDetails.ARGUMENT_ID}}",
            arguments = listOf(navArgument(RootScreen.ConcertDetails.ARGUMENT_ID) {
                type = NavType.IntType
            })
        ) { entry ->
            ConcertDetailsScreen(

            )
        }

    }
}
