package com.example.firstcomposeapp.ui.screens.custom_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp






fun Modifier.customAttribute()=this.layout { measurable, constraints ->
    val placeable=measurable.measure(constraints)
    layout(placeable.width,placeable.height){
        placeable.placeRelative(0,0)
    }
}



fun Modifier.marginEndBottom(margin:Dp)=this.layout { measurable, constraints ->
    val placeable=measurable.measure(constraints)
    val width=placeable.width+margin.roundToPx()
    val height=placeable.height+margin.roundToPx()
    layout(width,height){
        placeable.placeRelative(0,0)
    }
}




@Preview
@Composable
fun BodyContent(modifier: Modifier = Modifier) {

    /*Text(text = "Jetpack Compose",
        Modifier
            .background(Color.LightGray)
            .padding(20.dp)
    )*/
    MyDiagonalLayout(
        modifier
            .padding(8.dp)
    ) {
        Text("MyOwnColumn", Modifier.background(color = Color.Yellow).marginEndBottom(30.dp))
        Text("places items", Modifier.background(color = Color.Red))
        Text("vertically.", Modifier.background(color = Color.Green))
        Text("We've done it by hand!", Modifier.background(color = Color.Blue))
    }
}






@Composable
fun MyDiagonalLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0
        var width=0

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = width, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height

                width+=placeable.width
            }
        }
    }
}