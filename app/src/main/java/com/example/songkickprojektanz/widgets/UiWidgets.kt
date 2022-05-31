package com.example.songkickprojektanz.widgets

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.songkickprojektanz.Constants
import com.example.songkickprojektanz.R
import com.example.songkickprojektanz.data.local.ArtistsTopAlbum
import com.example.songkickprojektanz.data.local.FavouriteArtist
import com.example.songkickprojektanz.data.local.FavouriteTopAlbum
import com.example.songkickprojektanz.navigation.RootScreen
import com.example.songkickprojektanz.remote.responses.AlbumInfoResponse
import com.example.songkickprojektanz.screens.favourites.FavouritesViewModel
import com.example.songkickprojektanz.screens.home.CardArrow
import com.example.songkickprojektanz.screens.home.CardTitle
import com.example.songkickprojektanz.ui.theme.Black_light
import com.example.songkickprojektanz.ui.theme.Grey_light
import com.example.songkickprojektanz.ui.theme.White
import com.example.songkickprojektanz.utils.fonts
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun ImageItemShimmer() {
    Surface(color = colors.primary, modifier = Modifier.fillMaxSize(1f)) {
        Card(
            modifier = Modifier
                .fillMaxSize(1f)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true, color = Color.Black),
                    onClick = { }
                )
                .height(300.dp)
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = Color.White,
                    ),
                    color = Color.Gray
                ),

            elevation = 5.dp
        ) {}
    }
}

@Composable
fun ErrorDisplayingResultsImage() {
    Surface(color = colors.primary, modifier = Modifier.fillMaxSize(1f)) {
        Box(modifier = Modifier.fillMaxSize(1f)) {
            Image(
                painter = painterResource(id = R.drawable.no_results),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(1f)
            )
        }
    }
}

@Composable
fun ImageItem(
    albumCoverArt: String,
    albumName: String,
    artistName: String
) {
    Surface(color = colors.primary, modifier = Modifier.fillMaxSize(1f)) {
        Card(
            modifier = Modifier
                .fillMaxSize(1f)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true, color = Color.Black),
                    onClick = { }
                )
                .height(300.dp),
            elevation = 5.dp
        ) {
            Box(modifier = Modifier.height(10.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .crossfade(true)
                            .data(albumCoverArt)
                            .build(),
                        filterQuality = FilterQuality.High,
                        contentScale = ContentScale.Crop
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .fillMaxWidth(1f)
                )
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
                Text(
                    text = albumName,
                    color = Color.White,
                    fontFamily = fonts,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 160.dp, start = 10.dp)
                )
                Text(
                    text = artistName,
                    color = Color.White,
                    fontFamily = fonts,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 190.dp, start = 10.dp)
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Overview(
    overview: String
) {
    Surface(color = colors.primary, modifier = Modifier.fillMaxSize(1f)) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.height(300.dp)
        ) {
            SectionText(stringResource(R.string.overview))
            Text(
                text = overview,
                color = colors.onPrimary,
                textAlign = TextAlign.Justify,
                fontFamily = fonts,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 10.dp
                    )
                    .fillMaxWidth(.8f),
                softWrap = true
            )
        }
    }
}

@Composable
fun SectionText(text: String) {
    Text(
        text = text,
        color = colors.onPrimary,
        textAlign = TextAlign.Start,
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        modifier = Modifier
            .padding(start = 16.dp)
    )
}

@Composable
fun SongsItem(
    trackName: String,
    albumCoverArt: String,
    trackDuration: String,
    artistName: String,
    navController: NavController
) {
    Surface(color = colors.primaryVariant, modifier = Modifier.fillMaxSize(1f)) {
        Card(
            modifier = Modifier
                .size(width = 140.dp, height = 220.dp)
                .padding(horizontal = 5.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true, color = Color.Black),
                    onClick = {
                        navController.navigate("${RootScreen.TrackInfo.route}/${artistName}/${trackName}")
                    }
                ),
            shape = RoundedCornerShape(6.dp),
            elevation = 15.dp,
            backgroundColor = colors.primaryVariant
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .crossfade(true)
                            .data(albumCoverArt)
                            .build(),
                        filterQuality = FilterQuality.High,
                        contentScale = ContentScale.Crop

                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(shape = RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Crop,

                    )
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp, start = 5.dp, end = 5.dp),
                    text = trackName,
                    fontSize = 14.sp,
                    fontFamily = fonts,
                    color = colors.onPrimary
                )
                Text(
                    modifier = Modifier
                        .padding(
                            top = 5.dp,
                            start = 5.dp,
                            end = 5.dp
                        ),
                    text = trackDuration,
                    fontSize = 12.sp,
                    fontFamily = fonts,
                    color = colors.onPrimary
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun SongsSectionItem(
    list: AlbumInfoResponse?,
    navController: NavController,
    artistName: String
) {
    Surface(color = colors.primary, modifier = Modifier.fillMaxSize(1f)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 20.dp, bottom = 20.dp)
        ) {
            LazyRow(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(
                        top = 5.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {
                list?.albumInfo?.tracks?.let {
                    items(
                        it.track
                    ) { item ->
                        SongsItem(
                            trackName = item.name,
                            trackDuration = item.duration.toString(),
                            albumCoverArt = list.albumInfo.image!![2].photoUrl,
                            navController = navController,
                            artistName = artistName
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun FavouriteButton(
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    onClick: (isFav: Boolean) -> Unit = {}
) {

    IconButton(
        onClick = {
            onClick(isLiked)
        }) {
        Icon(
            tint = Color.White,
            imageVector = if (isLiked) {
                Icons.Filled.Favorite

            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableFavouritesCard(
    card: FavouriteArtist,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    navController: NavController,
    id: String,
) {
    val favouritesViewModel: FavouritesViewModel = hiltViewModel()
    val favoriteTopAlbums = favouritesViewModel.getArtistsTopAlbums(id).collectAsState(
        initial = ArtistsTopAlbum(
            topAlbum = emptyList(),
            favourite = FavouriteArtist(
                id = "",
                url = "",
                name = "",
                listeners = "",
                playCount = "",
                streamable = "",
                favourite = false,
            )
        )
    ).value
    val context = LocalContext.current

    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "transition")
    val cardBgColor by transition.animateColor({
        tween(durationMillis = Constants.EXPAND_ANIMATION_DURATION)
    }, label = "bgColorTransition") {
        Black_light
    }
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = Constants.EXPAND_ANIMATION_DURATION)
    }, label = "paddingTransition") {
        if (expanded) 48.dp else 24.dp
    }
    val cardElevation by transition.animateDp({
        tween(durationMillis = Constants.EXPAND_ANIMATION_DURATION)
    }, label = "elevationTransition") {
        if (expanded) 24.dp else 4.dp
    }
    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = Constants.EXPAND_ANIMATION_DURATION,
            easing = FastOutSlowInEasing
        )
    }, label = "cornersTransition") {
        if (expanded) 0.dp else 16.dp
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = Constants.EXPAND_ANIMATION_DURATION)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }

    Card(
        backgroundColor = cardBgColor,
        contentColor = colorResource(id = R.color.accent_red),
        elevation = cardElevation,
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = 8.dp
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
                            Toast.makeText(
                                context,
                                R.string.deleted_favourite_successfully,
                                Toast.LENGTH_SHORT
                            ).show()
                            return@FavouriteButton
                        } else {
                            card.playCount?.let {
                                FavouriteArtist(
                                    favourite = true,
                                    id = card.id,
                                    name = card.name,
                                    listeners = card.listeners,
                                    playCount = it,
                                    url = card.url,
                                    streamable = card.streamable,
                                )
                            }?.let {
                                favouritesViewModel.insertFavorite(
                                    it
                                )
                            }
                        }


                    })
            }
            favoriteTopAlbums.topAlbum.forEach {

                ExpandableFavouriteContent(
                    visible = expanded,
                    albumName = it.name,
                    albumCoverArt = it.image,
                    navController = navController,
                    artistName = it.artistName,
                    url = it.url,
                    playcount = it.playcount
                )
            }
        }
    }
}

@Composable
fun ExpandableFavouriteContent(
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
                durationMillis = Constants.FADE_IN_ANIMATION_DURATION,
                easing = FastOutLinearInEasing
            )
        )
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(Constants.EXPAND_ANIMATION_DURATION))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = Constants.FADE_OUT_ANIMATION_DURATION,
                easing = LinearOutSlowInEasing
            )
        )
    }
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(Constants.COLLAPSE_ANIMATION_DURATION))
    }
    AnimatedVisibility(
        visible = visible,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            //Spacer(modifier = Modifier.heightIn(100.dp))
            Row(
                modifier = Modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true, color = Color.Black),
                    onClick = {
                        navController.navigate("${RootScreen.AlbumInfo.route}/${artistName}/${albumName}")
                    }
                ),
            ) {
                Box(modifier = Modifier.wrapContentHeight()) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .crossfade(true)
                                .data(albumCoverArt)
                                .build(),
                            filterQuality = FilterQuality.High,
                            contentScale = ContentScale.FillBounds

                        ),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .clip(shape = RoundedCornerShape(6.dp))
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
                                R.string.deleted_favourite_successfully,
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
            Divider(color = Grey_light, thickness = 1.dp)
        }
    }
}
