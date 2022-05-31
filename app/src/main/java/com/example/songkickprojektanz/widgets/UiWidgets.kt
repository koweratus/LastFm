package com.example.songkickprojektanz.widgets

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
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
import com.example.songkickprojektanz.paging.Resource
import com.example.songkickprojektanz.remote.responses.AlbumInfoResponse
import com.example.songkickprojektanz.remote.responses.TopAlbumResponse
import com.example.songkickprojektanz.screens.favourites.FavouritesViewModel
import com.example.songkickprojektanz.screens.home.CardArrow
import com.example.songkickprojektanz.screens.home.CardTitle
import com.example.songkickprojektanz.screens.home.HomeViewModel
import com.example.songkickprojektanz.ui.theme.*
import com.example.songkickprojektanz.utils.fonts
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun CustomChip(
    selected: Boolean,
    text: String,
    onChecked: (Boolean) -> Unit,
) {
    // define properties to the chip
    // such as color, shape, width

    Surface(
        color = when {
            selected -> MaterialTheme.colors.onSurface
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.primary
            else -> Color.LightGray
        },
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> Color.LightGray
            }
        ),

        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable { onChecked(!selected) }
            .height(35.dp)
            .wrapContentWidth(),

        ) {
        // Add text to show the data that we passed
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(8.dp),
            fontFamily = fonts,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

    }
}

@Composable
fun CustomDialogScrollable(
    onConfirmClicked: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Black_light,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(.9f)
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                DialogImageItem()
                DialogTextButtonItem(
                    onDismiss,
                    text = "View Concert",
                    color = White,
                    buttonColors = Color.Transparent
                )
                Divider(color = Grey_light, thickness = 1.dp)
                DialogTextButtonItem(
                    onDismiss,
                    text = "Share",
                    color = White,
                    buttonColors = Color.Transparent
                )
                Divider(color = Grey_light, thickness = 1.dp)
                DialogTextButtonItem(
                    onDismiss,
                    text = "Save as Interested",
                    color = White,
                    buttonColors = Color.Transparent
                )
                Divider(color = Grey_light, thickness = 1.dp)
                DialogTextButtonItem(
                    onDismiss,
                    text = "Cancel",
                    color = Accent_orange,
                    buttonColors = Color.Transparent
                )
                Divider(color = Grey_light, thickness = 1.dp)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                DialogTextButtonItem(
                    onDismiss,
                    text = "Track This Artist",
                    color = colors.onPrimary,
                    buttonColors = Accent_pink
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
            }

        }
    }
}

@Composable
fun DialogTextItem(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.onSurface,
        modifier = Modifier.padding(10.dp),
        fontWeight = FontWeight.Medium,
        fontFamily = fonts,
        fontSize = 22.sp
    )
    Divider(color = Grey_light, thickness = 1.dp)
}

@Composable
fun DialogTextButtonItem(onDismiss: () -> Unit, text: String, color: Color, buttonColors: Color) {
    TextButton(
        onClick = onDismiss,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColors),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()


    ) {
        Text(
            text = text,
            //  modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            fontWeight = FontWeight.Medium,
            fontFamily = fonts,
            fontSize = 18.sp,
            color = color

        )
    }
}

@Composable
fun DialogImageItem() {
    Card(
        modifier = Modifier
            .height(300.dp)
            .width(200.dp)
            .padding(horizontal = 5.dp, vertical = 50.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.wrapContentHeight()) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = coil.request.ImageRequest.Builder(context = LocalContext.current)
                        .crossfade(true)
                        .data("https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@._V1_.jpg")
                        .build(),
                    filterQuality = FilterQuality.High,
                    contentScale = ContentScale.FillBounds

                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(6.dp))
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
        }
    }
}

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
            SectionText("Overview")
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
fun TopBilledCastItem(
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
fun TopBilledCastSectionItem(
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
                        TopBilledCastItem(
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
    val favoriteTopAlbums =   favouritesViewModel.getArtistsTopAlbums(id).collectAsState(
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
                                "Successfully deleted a favourite.",
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
                            )}


                    })

            }
            Divider(color = Grey_light, thickness = 1.dp)
        }
    }
}
