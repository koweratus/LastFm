package com.example.songkickprojektanz.widgets

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.songkickprojektanz.navigation.RootScreen
import com.example.songkickprojektanz.remote.responses.AlbumInfoResponse
import com.example.songkickprojektanz.ui.theme.*
import com.example.songkickprojektanz.utils.fonts
import com.google.accompanist.pager.ExperimentalPagerApi

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
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite

            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
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
fun ImageItem(
    albumCoverArt: String,
    albumName: String,
    albumReleaseDate: String,
    listeners: String
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
                Row(modifier = Modifier.padding(start = 16.dp, top = 105.dp)) {
                    Text(
                        text = listeners,
                        color = Color.White,
                        fontFamily = fonts,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 16.dp)
                    )
                }
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
                    text = albumReleaseDate,
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
        LazyColumn(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.height(300.dp)
        ) {

            item {
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
    artistName : String,
    navController: NavController
) {
    Surface(color = colors.primary, modifier = Modifier.fillMaxSize(1f)) {
        Card(
            modifier = Modifier
                .size(width = 140.dp, height = 220.dp)
                .padding(horizontal = 5.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true, color = Color.Black),
                    onClick = { navController.navigate("${RootScreen.TrackInfo.route}/${artistName}/${trackName}")
                    }
                ),
            shape = RoundedCornerShape(6.dp),
            elevation = 15.dp,
            backgroundColor = colors.primary
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
