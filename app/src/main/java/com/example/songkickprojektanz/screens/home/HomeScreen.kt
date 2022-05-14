package com.example.songkickprojektanz.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.songkickprojektanz.ui.theme.Grey_light
import com.example.songkickprojektanz.utils.fonts
import com.example.songkickprojektanz.widgets.CustomChip
import com.example.songkickprojektanz.widgets.CustomDialogScrollable
import com.example.songkickprojektanz.widgets.FavoriteButton
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagerApi
@Composable
fun HomeScreen() {

    val pagerStateFirstTab = rememberPagerState(initialPage = 0)
    val listSecondTab = listOf("Movies", "Tv")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp)
    )
    {
        val textChipRememberOneState = remember {
            mutableStateOf(false)
        }

        Tabs(pagerState = pagerStateFirstTab, listSecondTab)
        Row(
            modifier = Modifier.padding(start = 10.dp, top = 25.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            // creates a custom chip for active state
            CustomChip(
                selected = textChipRememberOneState.value,
                text = "Location",
                onChecked = {
                    textChipRememberOneState.value = it
                }
            )
            // Creates a custom chip for inactive state
            CustomChip(
                selected = false,
                text = "Dates",
                onChecked = {
                    textChipRememberOneState.value = it
                }
            )
        }
        Row(
            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            // creates a custom chip for active state
            Text(
                text = "March",
                color = Grey_light,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(8.dp),
                fontFamily = fonts,
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "16 Concerts",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(8.dp),
                fontFamily = fonts,
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium
            )
        }
        TabsContent(pagerState = pagerStateFirstTab, listSecondTab.size)
        Spacer(modifier = Modifier.padding(10.dp))


    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, count: Int) {

    HorizontalPager(count, state = pagerState, verticalAlignment = Alignment.Top) { page ->
        when (page) {
            0 -> RowSectionItem(list = createTestDataList())
            1 -> RowSectionItem(list = createTestDataList())
            2 -> RowSectionItem(list = createTestDataList())
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState, list: List<String>) {

    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        divider = {
            TabRowDefaults.Divider(
                thickness = 3.dp,
                color = Color.Transparent
            )
        },
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .wrapContentWidth(),
                height = 0.dp,
                color = Color.Transparent

            )

        },

        modifier = Modifier.padding(start = 10.dp)
    ) {
        list.forEachIndexed { index, _ ->

            Tab(
                text = {
                    Text(
                        text = list[index],
                        textAlign = TextAlign.Start,
                        color = if (pagerState.currentPage == index) Color(0xFFDBD9D9) else Color(
                            0xFF636366
                        ),
                        fontSize = 32.sp,
                        fontFamily = fonts,
                        fontWeight = FontWeight.Bold
                    )

                },
                modifier = Modifier
                    .wrapContentWidth(),
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagerApi
@Composable
fun RowSectionItem(
    list: List<Movies>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 10.dp)
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 64.dp
            ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.SpaceEvenly,
        )
        {
            items(
                items = list
            ) { item ->
                RowItem(moviesData = item)
            }


        }
    }
}

@Composable
fun RowItem(
    moviesData: Movies
) {
    val showDialog = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .size(width = 30.dp, height = 180.dp)
            .padding(horizontal = 5.dp, vertical = 5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = coil.request.ImageRequest.Builder(context = LocalContext.current)
                        .crossfade(true)
                        .data(moviesData.imageUrl)
                        .build(),
                    filterQuality = FilterQuality.High,
                    contentScale = ContentScale.FillBounds

                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(6.dp))
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, color = Color.Black),
                        onClick = {
                            showDialog.value = true
                        }
                    ),
            )

            if (showDialog.value) {
                //AppDialog(dialogState = true, modifier = Modifier.fillMaxSize(.8f))
                CustomDialogScrollable(
                    onDismiss = { showDialog.value = false },
                    onConfirmClicked = { showDialog.value = false })


            }
            FavoriteButton(modifier = Modifier.padding(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
        }
    }

}


fun createTestDataList(): List<Movies> {
    val list = mutableListOf<Movies>()
    val actors = listOf("Robert Downey Jr.", "Terrance Howard", "Mate Kovilic", "Mi vi oni")

    list.add(
        Movies(
            "Iron Man",
            actors,
            "https://upload.wikimedia.org/wikipedia/en/0/02/Iron_Man_%282008_film%29_poster.jpg"
        )
    )
    list.add(
        Movies(
            "Iron Man 2",
            actors,
            "https://upload.wikimedia.org/wikipedia/en/e/ed/Iron_Man_2_poster.jpg"
        )
    )
    list.add(
        Movies(
            "Iron Man 3",
            actors,
            "https://m.media-amazon.com/images/M/MV5BMjE5MzcyNjk1M15BMl5BanBnXkFtZTcwMjQ4MjcxOQ@@._V1_.jpg"
        )
    )
    list.add(
        Movies(
            "Batman 3",
            actors,
            "https://m.media-amazon.com/images/M/MV5BMTk4ODQzNDY3Ml5BMl5BanBnXkFtZTcwODA0NTM4Nw@@._V1_FMjpg_UX1000_.jpg"
        )
    )
    list.add(
        Movies(
            "Batman 2",
            actors,
            "https://upload.wikimedia.org/wikipedia/en/1/1c/The_Dark_Knight_%282008_film%29.jpg"
        )
    )
    list.add(
        Movies(
            "Batman",
            actors,
            "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg"
        )
    )
    list.add(
        Movies(
            "Joker",
            actors,
            "https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg"
        )
    )
    return list
}

data class Movies(
    val description: String,
    val actors: List<String>,
    val imageUrl: String
)
