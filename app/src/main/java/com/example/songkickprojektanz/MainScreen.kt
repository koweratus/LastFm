package com.example.songkickprojektanz

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.songkickprojektanz.navigation.BottomBarNavigationGraph
import com.example.songkickprojektanz.navigation.BottomBarScreen
import com.example.songkickprojektanz.ui.theme.Black
import com.example.songkickprojektanz.ui.theme.Black_light
import com.example.songkickprojektanz.ui.theme.White
import com.example.songkickprojektanz.utils.fonts

@ExperimentalFoundationApi
@Composable
fun MainScreen(navHostController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        topBar = { TopBar(navController = navHostController) }
    ) {
        BottomBarNavigationGraph(
            bottomBarNavHostController = navController,
            rootNavHostController = navHostController
        )
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Favourites
    )
    BottomNavigation(
        backgroundColor = Black,
        contentColor = Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontFamily = fonts,
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = Color(0xFFDBD9D9),
                unselectedContentColor = Color(0xFFDBD9D9).copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun TopBar(navController: NavController) {
    var canPop by remember { mutableStateOf(false) }
    navController.addOnDestinationChangedListener { controller, _, _ ->
        canPop = controller.previousBackStackEntry != null
    }
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentSize()
    ) {
        TopAppBar(
            title = {Text(text = "")

            },
            navigationIcon = if (canPop) {
                {
                    IconButton(
                        onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = White

                        )
                    }
                }
            } else {
                null
            },

            backgroundColor = Black,
            contentColor = colorResource(R.color.white),
        )
        Image(
            painterResource(R.drawable.lastfm_logo),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .height(50.dp)
                .fillMaxWidth(.6f)
        )
    }
}
