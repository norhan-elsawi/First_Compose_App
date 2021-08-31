package com.example.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeapp.ui.screens.ConstraintLayoutScreen
import com.example.firstcomposeapp.ui.screens.DecoupledConstraintScreen
import com.example.firstcomposeapp.ui.screens.RowArrangementScreen
import com.example.firstcomposeapp.ui.screens.TestScreen
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme

class TestActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApp()
        }
    }

    @Composable
    fun ComposeApp() {
        FirstComposeAppTheme {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "row_arrangement") {
                composable(route = "row_arrangement") {
                    RowArrangementScreen(navController)
                }
                composable(route = "test") {
                    TestScreen()
                }
                composable(route = "constraint_layout") {
                    ConstraintLayoutScreen()
                }
                composable(route = "decoupled_constraint") {
                    DecoupledConstraintScreen()
                }
            }
        }
    }
}

