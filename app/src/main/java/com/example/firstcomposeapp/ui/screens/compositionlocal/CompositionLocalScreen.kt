package com.example.firstcomposeapp.ui.screens.compositionlocal

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.example.firstcomposeapp.User

val LocalActiveUser = compositionLocalOf<User> { error("no user found") }

@Composable
fun CompositionLocalScreen() {
    val user = User("norhan", 28)
    CompositionLocalProvider(LocalActiveUser provides user) {
        MyUserScreen()
    }
    //MyUserScreen()
}

@Composable
fun MyUserScreen() {
    Column {
        Text("name: " + LocalActiveUser.current.name)
        Age()

        CompositionLocalExample()
    }
}

@Composable
fun Age() {
    Text("age: " + LocalActiveUser.current.age)
}

@Composable
fun CompositionLocalExample() {
    // MaterialTheme sets ContentAlpha.high as default
    Column {
        Text("Uses MaterialTheme's provided alpha")
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text("Medium value provided for LocalContentAlpha")
            Text("This Text also uses the medium value")
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                DescendantExample()
            }
        }
    }
}

@Composable
fun DescendantExample() {
    // CompositionLocalProviders also work across composable functions
    Text("This Text uses the disabled alpha now")
//    LocalContext.current
//    LocalConfiguration.current
//    LocalLifecycleOwner
}