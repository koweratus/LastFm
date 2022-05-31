package com.example.songkickprojektanz.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.navigation.NavController
import com.example.songkickprojektanz.screens.home.ExpandableCard
import com.example.songkickprojektanz.screens.home.HomeViewModel

@Composable
fun SearchScreen( navController: NavController) {
    val viewModel: SearchViewModel = hiltViewModel()
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = { SearchAppBar() }
        ) {
            Box {
                SearchList(viewModel, navController = navController)
            }
        }
    }
}

@Composable
private fun SearchList(
    viewModel: SearchViewModel,
    navController: NavController
) {
    val actorsList = viewModel.searchSearch.value.collectAsLazyPagingItems()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val expandedCardIds = homeViewModel.expandedCardIdsList.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(bottom = 48.dp)
    ) {

        items(actorsList) { actor ->
            ExpandableCard(
                card = actor!!,
                onCardArrowClick = { homeViewModel.onCardArrowClicked(actor.listeners.toInt()) },
                expanded = expandedCardIds.value.contains(actor.listeners.toInt()),
                navController,
                artistName = actor.name
            )
        }
    }
}

@Composable
fun SearchAppBar() {
    val viewModel: SearchViewModel = hiltViewModel()

    var query: String  by rememberSaveable {mutableStateOf("")}
    var showClearIcon by rememberSaveable { mutableStateOf(false)}

    if (query.isEmpty()) {
        showClearIcon = false
    } else if (query.isNotEmpty()) {
        showClearIcon = true
    }

    TextField(
        value = query,
        onValueChange = { onQueryChanged ->
            // If user makes changes to text, immediately updated it.
            query = onQueryChanged
            // To avoid crash, only query when string isn't empty.
            if (onQueryChanged.isNotEmpty()) {
                // Pass latest query to refresh search results.
                viewModel.searchAll(onQueryChanged)
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = { query = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = "Clear Icon"
                    )
                }
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        placeholder = { Text(text = "Search for artist...") },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background, shape = RectangleShape)
    )
}
