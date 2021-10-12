package com.example.firstcomposeapp.ui.screens.emptyScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun EmptyDataScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "No data found!",
            style = TextStyle(fontWeight = FontWeight.Black),
            modifier = Modifier.align(Alignment.Center))
    }
}