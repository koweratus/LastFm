package com.example.songkickprojektanz.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.songkickprojektanz.Constants.SPLASH_SCREEN_DURATION
import com.example.songkickprojektanz.R
import com.example.songkickprojektanz.navigation.RootScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen(
    navController: NavController,
) {
    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize(1f)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val scale = remember {
                Animatable(0f)
            }
            val overshootInterpolator = remember {
                OvershootInterpolator(2.5f)
            }

            LaunchedEffect(key1 = true) {
                withContext(Dispatchers.Main) {
                    scale.animateTo(
                        targetValue = 1.5f,
                        animationSpec = tween(
                            durationMillis = 700,
                            easing = {
                                overshootInterpolator.getInterpolation(it)
                            })
                    )
                    delay(SPLASH_SCREEN_DURATION)
                    navController.popBackStack()
                    navController.navigate(RootScreen.Main.route)
                }
            }
            Image(
                painterResource(
                    R.drawable.lastfm_logo
                ),
                contentDescription = "App-logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.large_325))
                    .padding(dimensionResource(id = R.dimen.small_100))
            )
        }
    }
}
