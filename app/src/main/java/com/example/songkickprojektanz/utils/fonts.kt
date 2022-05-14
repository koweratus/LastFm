package com.example.songkickprojektanz.utils

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.songkickprojektanz.R

val fonts = FontFamily(
    Font(R.font.sfpro),
    Font(R.font.sfpro_bold, weight = FontWeight.Bold),
    Font(R.font.sfpro_medium, weight = FontWeight.Medium),
    Font(R.font.sfpro_black_italic, weight = FontWeight.W900, style = FontStyle.Italic),
)
