package com.example.songkickprojektanz.screens.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.example.songkickprojektanz.Constants.COLLAPSE_ANIMATION_DURATION
import com.example.songkickprojektanz.Constants.EXPAND_ANIMATION_DURATION
import com.example.songkickprojektanz.Constants.FADE_IN_ANIMATION_DURATION
import com.example.songkickprojektanz.Constants.FADE_OUT_ANIMATION_DURATION
import com.example.songkickprojektanz.R
import com.example.songkickprojektanz.data.local.FavouriteArtist
import com.example.songkickprojektanz.data.local.FavouriteTopAlbum
import com.example.songkickprojektanz.model.Artist
import com.example.songkickprojektanz.navigation.RootScreen
import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.responses.TopAlbumResponse
import com.example.songkickprojektanz.screens.favourites.FavouritesViewModel
import com.example.songkickprojektanz.screens.search.SearchScreen
import com.example.songkickprojektanz.ui.theme.Black_light
import com.example.songkickprojektanz.ui.theme.Grey_light
import com.example.songkickprojektanz.ui.theme.White
import com.example.songkickprojektanz.utils.fonts
import com.example.songkickprojektanz.widgets.FavouriteButton
import com.google.accompanist.pager.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    navController: NavController,
) {
    Scaffold(
        backgroundColor = Color(
            ContextCompat.getColor(
                LocalContext.current,
                R.color.black
            )
        )
    ) {
        val viewModel: HomeViewModel = hiltViewModel()
        val expandedCardIds = viewModel.expandedCardIdsList.collectAsState()
        val topArtists = viewModel.topArtists.collectAsLazyPagingItems()

        val pagerStateFirstTab = rememberPagerState(initialPage = 0)
        val listSecondTab =
            listOf(stringResource(R.string.top_artists), stringResource(R.string.search))

        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight()
                .padding(top = dimensionResource(id = R.dimen.normal_125)),

            )
        {
            Tabs(pagerState = pagerStateFirstTab, listSecondTab)
            TabsContent(
                pagerState = pagerStateFirstTab, listSecondTab.size,
                topArtists, viewModel = viewModel, expandedCardIds = expandedCardIds, navController
            )

        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState, count: Int, list: LazyPagingItems<Artist>, viewModel: HomeViewModel,
    expandedCardIds: State<List<Int>>,
    navController: NavController
) {
    HorizontalPager(count, state = pagerState, verticalAlignment = Alignment.Top) { page ->
        when (page) {
            0 -> RowSectionItem(
                list,
                navController = navController,
                expandedCardIds = expandedCardIds,
                viewModel = viewModel
            )
            1 -> SearchScreen(navController)
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
                thickness = dimensionResource(id = R.dimen.small_50),
                color = Color.Transparent
            )
        },
        edgePadding = dimensionResource(id = R.dimen.small_0),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .wrapContentWidth(),
                height = dimensionResource(id = R.dimen.small_0),
                color = Color.Transparent

            )

        },

        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.small_112))
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

@ExperimentalPagerApi
@Composable
fun RowSectionItem(
    list: LazyPagingItems<Artist>,
    viewModel: HomeViewModel,
    expandedCardIds: State<List<Int>>,
    navController: NavController
) {
    Column {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        )
        {
            items(
                items = list
            ) { item ->
                ExpandableCard(
                    card = item!!,
                    onCardArrowClick = { viewModel.onCardArrowClicked(item.listeners.toInt()) },
                    expanded = expandedCardIds.value.contains(item.listeners.toInt()),
                    navController,
                    artistName = item.name
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(
    card: Artist,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    navController: NavController,
    artistName: String,
) {
    val favouritesViewModel: FavouritesViewModel = hiltViewModel()
    val context = LocalContext.current

    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "transition")
    val cardBgColor by transition.animateColor({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "bgColorTransition") {
        Black_light
    }
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "paddingTransition") {
        if (expanded) dimensionResource(id = R.dimen.large_150) else dimensionResource(id = R.dimen.normal_150)
    }
    val cardElevation by transition.animateDp({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "elevationTransition") {
        if (expanded) dimensionResource(id = R.dimen.normal_150) else dimensionResource(id = R.dimen.small_50)
    }
    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = EXPAND_ANIMATION_DURATION,
            easing = FastOutSlowInEasing
        )
    }, label = "cornersTransition") {
        if (expanded) dimensionResource(id = R.dimen.small_0) else dimensionResource(id = R.dimen.normal_100)
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }
    val viewModel: HomeViewModel = hiltViewModel()
    val topAlbums = produceState<Resource<TopAlbumResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.initArtistsTopAlbums(card.name)
    }.value

    Card(
        backgroundColor = cardBgColor,
        contentColor = colorResource(id = R.color.accent_red),
        elevation = cardElevation,
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = dimensionResource(id = R.dimen.small_100)
            ),
        onClick = onCardArrowClick
    ) {
        Column(Modifier.wrapContentHeight()) {

            Box {
                CardArrow(
                    degrees = arrowRotationDegree,
                    onClick = onCardArrowClick
                )
                CardTitle(title = card.name)
                FavouriteButton(isLiked =
                favouritesViewModel.isArtistFavourite(card.id)
                    .collectAsState(false).value
                        != null,
                    onClick = { isFav ->
                        if (isFav) {
                            favouritesViewModel.deleteOneArtist(card.id)
                            favouritesViewModel.deleteTopAlbum(card.id)
                            Toast.makeText(
                                context,
                                R.string.deleted_favourite_successfully,
                                Toast.LENGTH_SHORT
                            ).show()
                            return@FavouriteButton
                        } else {
                            favouritesViewModel.insertFavorite(
                                FavouriteArtist(
                                    favourite = true,
                                    id = card.id,
                                    name = card.name,
                                    listeners = card.listeners,
                                    playCount = card.playCount ?: "0",
                                    url = card.url,
                                    streamable = card.streamable,
                                )
                            )
                        }
                    })
            }
            topAlbums.data?.topAlbums?.album?.take(5)?.forEach {

                ExpandableContent(
                    visible = expanded,
                    albumName = it.name,
                    albumCoverArt = it.image[0].photoUrl,
                    navController = navController,
                    artistName = artistName,
                    url = it.url,
                    playcount = it.playcount
                )
            }
        }
    }
}

@Composable
fun CardArrow(
    degrees: Float,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_expand_less),
                contentDescription = "Expandable Arrow",
                modifier = Modifier.rotate(degrees),
            )
        },
        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.large_1064))
    )
}

@Composable
fun CardTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.normal_100)),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun ExpandableContent(
    visible: Boolean = true,
    albumName: String,
    albumCoverArt: String,
    navController: NavController,
    artistName: String,
    playcount: Int,
    url: String,
) {
    val favouritesViewModel: FavouritesViewModel = hiltViewModel()
    val context = LocalContext.current
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = FADE_IN_ANIMATION_DURATION,
                easing = FastOutLinearInEasing
            )
        )
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(EXPAND_ANIMATION_DURATION))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = FADE_OUT_ANIMATION_DURATION,
                easing = LinearOutSlowInEasing
            )
        )
    }
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(COLLAPSE_ANIMATION_DURATION))
    }
    AnimatedVisibility(
        visible = visible,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.small_100))
        ) {
            Row(
                modifier = Modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true, color = Color.Black),
                    onClick = {
                        navController.navigate("${RootScreen.AlbumInfo.route}/${artistName}/${albumName}")
                    }),
            ) {
                Box(modifier = Modifier.wrapContentHeight()) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = coil.request.ImageRequest.Builder(context = LocalContext.current)
                                .crossfade(true)
                                .data(albumCoverArt)
                                .build(),
                            filterQuality = FilterQuality.High,
                            contentScale = ContentScale.FillBounds
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.large_162))
                            .width(dimensionResource(id = R.dimen.large_162))
                            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.small_75)))
                    )
                }
                Text(
                    text = albumName,
                    textAlign = TextAlign.Center,
                    color = White,
                    fontFamily = fonts,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                FavouriteButton(isLiked =
                favouritesViewModel.isAlbumFavourite(albumName)
                    .collectAsState(false).value
                        != null,
                    onClick = { isFav ->
                        if (isFav) {
                            favouritesViewModel.deleteTopAlbum(albumName)
                            Toast.makeText(
                                context,
                                "Successfully deleted a favourite.",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@FavouriteButton
                        } else {
                            favouritesViewModel.insertTopAlbum(
                                FavouriteTopAlbum(
                                    favourite = true,
                                    name = albumName,
                                    playcount = playcount,
                                    url = url,
                                    image = albumCoverArt,
                                    id = 0,
                                    artistName = artistName
                                )
                            )
                        }
                    })
            }
            Divider(color = Grey_light, thickness = dimensionResource(id = R.dimen.small_1))
        }
    }
}
