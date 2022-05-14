package com.example.songkickprojektanz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.songkickprojektanz.navigation.RootNavigationGraph
import com.example.songkickprojektanz.ui.theme.SongkickProjektanzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SongkickProjektanzTheme {
                val navController = rememberNavController()
                RootNavigationGraph(rootNavHostController = navController)
            }
        }
    }
}