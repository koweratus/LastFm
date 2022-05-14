package com.example.songkickprojektanz.widgets

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.example.songkickprojektanz.ui.theme.Accent_orange
import com.example.songkickprojektanz.ui.theme.Accent_pink
import com.example.songkickprojektanz.ui.theme.Black_light
import com.example.songkickprojektanz.utils.fonts

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
fun AppDialog(
    modifier: Modifier = Modifier,
    dialogState: Boolean = false,
    onDialogPositiveButtonClicked: (() -> Unit)? = null,
    onDialogStateChange: ((Boolean) -> Unit)? = null,
    onDismissRequest: (() -> Unit)? = null,
) {
    val textPaddingAll = 24.dp
    val buttonPaddingAll = 8.dp
    val dialogShape = RoundedCornerShape(16.dp)

    if (dialogState) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
            },
            title = null,
            text = null,
            buttons = {

                Column {

                    Text(text = "Track", color = MaterialTheme.colors.onSurface)
                    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
                    Text(text = "Track", color = MaterialTheme.colors.onSurface)
                    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
                    Text(text = "Track", color = MaterialTheme.colors.onSurface)
                    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
                }

            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
            modifier = modifier,
            shape = dialogShape
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
                .fillMaxWidth(.8f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .weight(weight = 1f, fill = false)
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 5.dp, vertical = 5.dp),
                        shape = RoundedCornerShape(15.dp),
                        elevation = 5.dp
                    ) {
                        Box(modifier = Modifier.height(120.dp)) {
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
                    Text(
                        text = "Track",
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(bottom = 5.dp),
                        fontWeight = FontWeight.Medium,
                        fontFamily = fonts,
                        fontSize = 22.sp
                    )
                    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
                    Text(
                        text = "Track",
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(bottom = 5.dp),
                        fontWeight = FontWeight.Medium,
                        fontFamily = fonts,
                        fontSize = 22.sp
                    )
                    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
                    Text(
                        text = "Track",
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(bottom = 5.dp),
                        fontWeight = FontWeight.Medium,
                        fontFamily = fonts,
                        fontSize = 22.sp,

                    )
                    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
                   TextButton(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()

                    ) {
                        Text(
                            text = "Track ASAP",
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            fontWeight = FontWeight.Medium,
                            fontFamily = fonts,
                            fontSize = 18.sp,
                            color = Accent_orange

                            )
                    }
                    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Accent_pink),
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()

                    ) {
                        Text(
                            text = "Track ASAP",
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            fontWeight = FontWeight.Medium,
                            fontFamily = fonts,
                            fontSize = 18.sp,

                        )
                    }
                }
            }
        }
    }
}
