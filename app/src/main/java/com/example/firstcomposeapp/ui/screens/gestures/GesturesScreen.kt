package com.example.firstcomposeapp.ui.screens.gestures

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun GesturesScreen() {
    Column {
        ClickableSample()
        ScrollBoxes()
        ScrollableSample()
        NestedScroll()
    }
}

@Composable
fun ClickableSample() {
    val context = LocalContext.current
    val count = remember { mutableStateOf(0) }
    // content that you want to make clickable
    Text(
        text = count.value.toString(),
        modifier = Modifier
            .background(Color.Magenta)
            .padding(20.dp)
            .clickable { count.value += 1 }
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onPress = { showText("Start", context) },
//                    onDoubleTap = { showText("On Double Tap", context) },
//                    onLongPress = { showText("On Long Press", context) },
//                    onTap = { showText("On Tap", context) }
//                )
//            }
    )

}

@Composable
fun ScrollBoxes() {

//    // Smoothly scroll 500px on first composition
//    val state = rememberScrollState()
//    LaunchedEffect(Unit) { state.animateScrollTo(500) }
    Row(
        modifier = Modifier
            .padding(30.dp)
            .background(Color.LightGray)
            .width(150.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        repeat(10) {
            Text("Item $it", modifier = Modifier.padding(2.dp))
        }
    }
}

@Composable
fun ScrollableSample() {
    // actual composable state
    val offset = remember { mutableStateOf(0f) }
    Box(
        Modifier
            .padding(10.dp)
            .size(150.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                // Scrollable state: describes how to consume
                // scrolling delta and update offset
                state = rememberScrollableState { delta ->
                    offset.value += delta
                    delta
                }
            )
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(offset.value.toString())
    }
}

@Composable
fun NestedScroll() {
    val gradient = Brush.verticalGradient(0f to Color.Gray, 1000f to Color.White)
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        Column {
            repeat(6) {
                Box(
                    modifier = Modifier
                        .height(128.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Scroll here",
                        modifier = Modifier
                            .border(12.dp, Color.DarkGray)
                            .background(brush = gradient)
                            .padding(24.dp)
                            .height(150.dp)
                    )
                }
            }
        }
    }
}

fun showText(text: String, context: Context) {
    Toast.makeText(context, text,
        Toast.LENGTH_SHORT).show()
}
