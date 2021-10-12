package com.example.firstcomposeapp.data.remote

import com.example.firstcomposeapp.ui.screens.usersScreen.models.User
import retrofit2.http.GET


interface UsersService {

    @GET("users")
    suspend fun getListOfUsers(): List<User>
}