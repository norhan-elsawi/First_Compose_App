package com.example.firstcomposeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintLayoutScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (redBox, blueBox, yellowBox, text, boxWithConstraint) = createRefs()

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Red)
            .constrainAs(redBox) {})

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(redBox.bottom)
                start.linkTo(redBox.end)
            })

        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                baseline.linkTo(redBox.baseline)
                start.linkTo(blueBox.end, 20.dp)
            })

        Text("Hello World", modifier = Modifier.constrainAs(text) {
            top.linkTo(parent.top)
            start.linkTo(yellowBox.end)
        })

        BoxWithConstraintsDemo(Modifier.constrainAs(boxWithConstraint) {
            top.linkTo(blueBox.bottom)
        })
    }
}

@Composable
fun BoxWithConstraintsDemo(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        Column {
            MyBoxWithConstraintsDemo()
        }

        Text("Here we set the size to 150.dp", modifier = Modifier.padding(top = 20.dp))

        Column(
            modifier = Modifier
                .size(150.dp)
        ) {
            MyBoxWithConstraintsDemo()
        }
    }
}

@Composable
private fun MyBoxWithConstraintsDemo() {
    BoxWithConstraints {
        val boxWithConstraintsScope = this
        //You can use this scope to get the minWidth, maxWidth, minHeight, maxHeight in dp and constraints

        Column {
            if (boxWithConstraintsScope.maxHeight >= 200.dp) {
                Text(
                    "This is only visible when the maxHeight is >= 200.dp",
                    style = TextStyle(fontSize = 20.sp)
                )
            }
            Text("minHeight: ${boxWithConstraintsScope.minHeight}, maxHeight: ${boxWithConstraintsScope.maxHeight},  minWidth: ${boxWithConstraintsScope.minWidth} maxWidth: ${boxWithConstraintsScope.maxWidth}")
        }
    }
}