package com.example.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeapp.ui.screens.ConstraintLayoutScreen
import com.example.firstcomposeapp.ui.screens.DecoupledConstraintScreen
import com.example.firstcomposeapp.ui.screens.RowArrangementScreen
import com.example.firstcomposeapp.ui.screens.TestScreen
import com.example.firstcomposeapp.ui.screens.compositionlocal.CompositionLocalScreen
import com.example.firstcomposeapp.ui.screens.form.FormValidationScreen
import com.example.firstcomposeapp.ui.screens.gestures.Gestures2Screen
import com.example.firstcomposeapp.ui.screens.gestures.GesturesScreen
import com.example.firstcomposeapp.ui.screens.images.ImagesScreen
import com.example.firstcomposeapp.ui.screens.images.RememberScreen
import com.example.firstcomposeapp.ui.screens.paging.EmployeeListScreen
import com.example.firstcomposeapp.ui.screens.usersScreen.UsersScreen
import com.example.firstcomposeapp.ui.screens.usersScreen.UsersViewModel
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : ComponentActivity() {

    private val usersViewModel: UsersViewModel by viewModels()

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
            NavHost(navController, startDestination = "users_screen") {

                composable(route = "users_screen") {
                    UsersScreen(usersViewModel)
                }

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

                composable(route = "images_screen") {
                    ImagesScreen()
                }

                composable(route = "remember_screen") {
                    RememberScreen()
                }

                composable(route = "form") {
                    FormValidationScreen()
                }

                composable(route = "composition_local") {
                    CompositionLocalScreen()
                }

                composable(route = "employee_screen") {
                    EmployeeListScreen()
                }

                composable(route = "gestures_screen") {
                    GesturesScreen()
                }

                composable(route = "gestures2_screen") {
                    Gestures2Screen()
                }

            }
        }
    }
}

