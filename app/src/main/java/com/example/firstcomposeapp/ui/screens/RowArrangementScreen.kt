package com.example.firstcomposeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
                    RowContent(
                        arrangement
                    )
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
    rowArrangement: RowArrangement
) {
    Row(
        modifier = Modifier
            .background(color = Color.Blue)
            .height(60.dp)
            .fillMaxWidth(),
        horizontalArrangement = rowArrangement.arrangement,
        verticalAlignment = Alignment.Top
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
