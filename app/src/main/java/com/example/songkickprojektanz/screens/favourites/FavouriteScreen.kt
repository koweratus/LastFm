package com.example.songkickprojektanz.screens.favourites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.songkickprojektanz.R
import com.example.songkickprojektanz.data.local.FavouriteArtist
import com.example.songkickprojektanz.screens.home.HomeViewModel
import com.example.songkickprojektanz.widgets.ExpandableFavouritesCard
import com.example.songkickprojektanz.widgets.SectionText
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun FavouriteScreen(navController: NavController) {

    val viewModel: FavouritesViewModel = hiltViewModel()
    val favoriteArtist = viewModel.favourites.collectAsState(initial = emptyList())
    val homeViewModel: HomeViewModel = hiltViewModel()
    val expandedCardIds = homeViewModel.expandedCardIdsList.collectAsState()

    Column(
        Modifier.padding(
            top = dimensionResource(id = R.dimen.large_162),
            bottom = dimensionResource(id = R.dimen.large_125)
        )
    ) {
        SectionText(stringResource(R.string.favourites))
        LazyColumn(
            modifier = Modifier.padding(bottom =  dimensionResource(id = R.dimen.large_150))
        ) {
            items(items = favoriteArtist.value, key = { favoriteFilm: FavouriteArtist ->
                favoriteFilm.id
            }) { favourite ->
                ExpandableFavouritesCard(
                    card = favourite,
                    onCardArrowClick = { homeViewModel.onCardArrowClicked(favourite.listeners.toInt()) },
                    expanded = expandedCardIds.value.contains(favourite.listeners.toInt()),
                    navController,
                    id = favourite.name
                )
            }
        }
    }
}
