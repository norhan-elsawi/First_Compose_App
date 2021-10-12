package com.example.firstcomposeapp.ui.screens.usersScreen

import com.example.firstcomposeapp.data.remote.UsersService
import com.example.firstcomposeapp.utils.ResultHandler
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val resultHandler: ResultHandler,
    private val usersService: UsersService,
) {

    suspend fun getUsers() = resultHandler.resultHandler {
        usersService.getListOfUsers()
    }

}