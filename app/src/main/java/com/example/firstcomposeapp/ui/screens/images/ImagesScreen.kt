package com.example.firstcomposeapp.ui.screens.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme

@Composable
fun TextWithStyle(textString: String) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = textString,
        style = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.Black,
            fontSize = 18.sp,
        ),
    )
}

@Composable
fun Column_scrollable(content: @Composable() () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical,
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun Row_center_gravity(content: @Composable() () -> Unit) {
    Surface(modifier = Modifier.padding(2.dp),
        color = Color.LightGray.copy(alpha = 0.5f),
        shape = RoundedCornerShape(20.dp)) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImagesScreen() {
    FirstComposeAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            //https://medium.com/mobile-app-development-publication/jetpack-compose-image-content-scaletype-fully-illustrated-bfdf2de7ef5
            // TODO XML Image ScaleType FitStart, FitEnd, and FitCenter
            /*
            *  In Jetpack Compose, we just need to have Fit,
            *  and use alignment to decide if it is start or end.
            *
            * */

            //TODO XML Image ScaleType has CenterCrop
            /*
            * there is no StartCrop or EndCrop
            *
            *  In Jetpack Compose, we have Crop,
            *  and we can use alignment to get the start or end we need
            * */

            Row_center_gravity {
                TextWithStyle(textString = "Crop - Center Crop from start")
                ImageWithScale(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart,
                    height = 300.dp,
                    width = 100.dp,
                    painter = painterResource(id = R.drawable.ic_box_svg))
            }

            Row_center_gravity {
                TextWithStyle(textString = "Crop - Center Crop from end")
                ImageWithScale(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterEnd,
                    height = 300.dp,
                    width = 100.dp,
                    painter = painterResource(id = R.drawable.ic_box_svg))
            }

            Row_center_gravity {
                TextWithStyle(textString = "FillWidth")
                ImageWithScale(contentScale = ContentScale.FillWidth,
                    width = 500.dp)
            }

            Row_center_gravity {
                TextWithStyle(textString = "Fit - End")
                ImageWithScale(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.CenterEnd,
                    width = 500.dp,
                    height = 200.dp,
                    painter = painterResource(id = R.drawable.abc_vector_test))
            }

            Row_center_gravity {
                TextWithStyle(textString = "Fit - Start")
                ImageWithScale(
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.CenterStart,
                    width = 500.dp,
                    height = 200.dp,
                    painter = painterResource(id = R.drawable.abc_vector_test))
            }

            Row_center_gravity {
                TextWithStyle(textString = "FillHeight")
                ImageWithScale(contentScale = ContentScale.FillHeight,
                    height = 400.dp,
                    painter = painterResource(id = R.drawable.abc_vector_test))
            }



            Row_center_gravity {
                TextWithStyle(textString = "Inside")
                ImageWithScale(contentScale = ContentScale.Inside)
            }

            Row_center_gravity {
                TextWithStyle(textString = "FillBounds")
                ImageWithScale(contentScale = ContentScale.FillBounds)
            }

            Row_center_gravity {
                TextWithStyle(textString = "FillBounds")
                ImageWithScale(contentScale = ContentScale.FillBounds, width = 40.dp)
            }
        }
    }
}

@Composable
fun ImageWithScale(
    contentScale: ContentScale,
    alignment: Alignment = Alignment.Center,
    width: Dp = 100.dp,
    height: Dp = 100.dp,
    painter: Painter? = null,
) {

    val image: Painter = painterResource(id = R.drawable.abc_vector_test)

    Image(
        modifier = Modifier
            .size(width = width, height = height)
            .background(Color.Red),
        painter = painter ?: image,
        contentDescription = "",
        contentScale = contentScale,
        alignment = alignment
    )
}