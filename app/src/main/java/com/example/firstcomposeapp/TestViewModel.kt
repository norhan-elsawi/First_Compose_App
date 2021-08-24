package com.example.firstcomposeapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class TestViewModel : ViewModel() {
    val loading = mutableStateOf(false)
    val users = mutableStateOf<ArrayList<User>>(arrayListOf())
    val page = mutableStateOf(0)

    suspend fun getUsers() {
        loading.value = true
        delay(2000)
        User.getRandomList().also {
            users.value += it
            page.value += 1
        }
        loading.value = false
    }
}