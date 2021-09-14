package com.example.firstcomposeapp.ui.screens.images

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val TAG = "remember"


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun showHide() {
    var isAdmin by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        //TODO rounded shape
        Box(Modifier
            .size(200.dp)
            .animateContentSize()) {
            Surface(modifier = Modifier.fillMaxSize(),
                color = Color.Blue) {}
        }


        AnimatedVisibility(
            visible = isAdmin,
            enter = expandVertically(animationSpec = tween(durationMillis = 2000)),
            exit = shrinkHorizontally(),
        ) {
            Box(Modifier
                .size(200.dp)
                .animateContentSize()) {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color.Yellow) {}
            }
        }


        //TODO divider
        Box(modifier = Modifier.requiredHeight(height = 100.dp))

        //TODO Button
        Button(onClick = {
            isAdmin = !isAdmin
            Log.e(TAG, "doSomething: button clicked")
        }) {
            Text(text = "Change Size")
        }
    }
}


@Composable
fun doSomething() {
    var size = rememberSaveable { mutableStateOf(500) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        //TODO rounded shape
        Box(Modifier
            .size(size = size.value.dp)
            .animateContentSize()) {
            Surface(modifier = Modifier.fillMaxSize(),
                color = Color.Blue,
                shape = RoundedCornerShape(10.dp)) {}
        }

        //TODO divider
        Box(modifier = Modifier.requiredHeight(height = 100.dp))

        //TODO Button
        Button(onClick = {

            if (size.value == 100)
                size.value = 500
            else size.value = 100
            Log.e(TAG, "doSomething: button clicked")
        }) {
            Text(text = "Change Size")
        }

    }

}

/*
*
*
*
* Images(scaleType)
* remember -> mutableStates
* animatedVisiablity
* show hide -> mutableState -> remember
*
*  remember stores objects in the Composition,
*  and forgets the object when the composable that called remember is removed from the Composition.
*
*  mutableStateOf
*  creates an observable MutableState<T>, which is an observable type integrated with the compose runtime
*  Any changes to value will schedule recomposition of any composable functions that read value
*
*
*   next session
* LocalProviders
*  observing ( LiveData - Flow - Rx)
*  TextFields
*  basic animations
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* */


@Preview(showBackground = true)
@Composable
fun RememberScreen() {
    showHide()
//    doSomething()
//    doSomethingLive()
}

@Composable
fun doSomethingLive() {
    var size by remember { mutableStateOf(100.dp) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        Box(Modifier
            .size(size = size)
            .animateContentSize()) {
            Surface(modifier = Modifier.fillMaxSize(),
                color = Color.Blue,
                shape = RoundedCornerShape(10.dp)) {}
        }

        Box(modifier = Modifier.requiredHeight(height = 100.dp))

        Button(onClick = {
            size =
                if (size == 100.dp)
                    500.dp
                else 100.dp

            Log.e(TAG, "doSomething Live : button clicked")
        }) {
            Text(text = "Change Size")
        }

    }
}