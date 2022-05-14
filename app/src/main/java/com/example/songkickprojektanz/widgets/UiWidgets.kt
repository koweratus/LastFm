package com.example.songkickprojektanz.widgets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import com.example.songkickprojektanz.ui.theme.*
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
                    DialogTextButtonItem(onDismiss , text = "View Concert" ,color = White, buttonColors = Color.Transparent)
                    Divider(color = Grey_light, thickness = 1.dp)
                    DialogTextButtonItem(onDismiss , text = "Share" ,color = White, buttonColors = Color.Transparent)
                    Divider(color = Grey_light, thickness = 1.dp)
                    DialogTextButtonItem(onDismiss , text = "Save as Interested" ,color = White, buttonColors = Color.Transparent)
                    Divider(color = Grey_light, thickness = 1.dp)
                    DialogTextButtonItem(onDismiss , text = "Cancel" ,color = Accent_orange, buttonColors = Color.Transparent)
                    Divider(color = Grey_light, thickness = 1.dp)
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                   DialogTextButtonItem(onDismiss, text = "Track This Artist" , color = colors.onPrimary, buttonColors = Accent_pink)
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
    Divider(color = Grey_light, thickness = 1.dp, )
}

@Composable
fun DialogTextButtonItem( onDismiss: () -> Unit,text: String, color: Color, buttonColors: Color){
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
fun DialogImageItem(){
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
