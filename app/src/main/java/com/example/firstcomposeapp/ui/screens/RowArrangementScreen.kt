package com.example.firstcomposeapp.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RowArrangementScreen(navController: NavController) {
    Column {
        LetterBoxRow()
        Button(
            onClick = { navController.navigate("test") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = "go to Test Screen")
        }
        Button(
            onClick = { navController.navigate("constraint_layout") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = "go to Constraint Layout Screen")
        }
        Button(
            onClick = { navController.navigate("decoupled_constraint") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = "go to Decoupled Constraint Screen")
        }
    }
}

sealed interface RowArrangement {
    val arrangement: Arrangement.Horizontal
    val description: String
}

class HorizontalArrangement(
    value: Arrangement.Horizontal,
) : RowArrangement {
    override val arrangement: Arrangement.Horizontal = value
    override val description: String = arrangement.toString().substringAfter("#")
}

object EqualWeight : RowArrangement {
    override val arrangement = Arrangement.Start
    override val description: String = "Equal Weight"
}


@Composable
fun LetterBoxRow() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        listOf(
            EqualWeight,
            HorizontalArrangement(Arrangement.SpaceBetween),
            HorizontalArrangement(Arrangement.SpaceAround),
            HorizontalArrangement(Arrangement.SpaceEvenly),
            HorizontalArrangement(Arrangement.End),
            HorizontalArrangement(Arrangement.Center),
            HorizontalArrangement(Arrangement.Start),
        ).forEach { arrangement: RowArrangement ->
            ShowcaseRow(
                rowContentDescriptionText = arrangement.description,
                rowContent = {
                    BoxWithConstraints {
                        val box = this
                        RowContent(
                            box,
                            arrangement
                        )
                    }
                },
            )
        }
    }
}

@Composable
fun ShowcaseRow(
    rowContentDescriptionText: String,
    rowContent: @Composable RowScope.() -> Unit,
) {
    Row {
        Text(
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            text = rowContentDescriptionText
        )
        Box(
            modifier = Modifier.weight(4f)
        ) {
            this@Row.rowContent()
        }
    }
}

@Composable
fun RowContent(
    parent: BoxWithConstraintsScope,
    rowArrangement: RowArrangement
) {

    val infiniteTransition = rememberInfiniteTransition()

    val width by infiniteTransition.animateFloat(
        initialValue = parent.maxWidth.value,
        targetValue = parent.maxWidth.value / 2,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        modifier = Modifier
            .background(color = Color.Blue)
            .height(60.dp)
            .width(width.dp)
            .fillMaxWidth(),
        horizontalArrangement = rowArrangement.arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf('A', 'B', 'C').forEach { char ->
            LetterBox(
                modifier = when (rowArrangement) {
                    EqualWeight -> Modifier
                        .fillMaxWidth()
                        .weight(1f)
                    is HorizontalArrangement -> Modifier.width(30.dp)
                },
                letter = char
            )
        }
    }
}

@Composable
fun LetterBox(
    modifier: Modifier,
    letter: Char,
) {
    Box(
        modifier = modifier
            .padding(6.dp)
            .clip(MaterialTheme.shapes.small)
            .background(Color.Yellow)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            text = letter.toString(),
            textAlign = TextAlign.Center
        )
    }
}
