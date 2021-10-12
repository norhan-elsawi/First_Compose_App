package com.example.firstcomposeapp.ui.screens.usersScreen

import com.example.firstcomposeapp.base.BaseComposeViewModel
import com.example.firstcomposeapp.utils.ContextProviders
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    contextProviders: ContextProviders,
) : BaseComposeViewModel<UsersScreenState>(
    initialState = UsersScreenState(),
    contextProviders = contextProviders) {

    suspend fun getUsersResult() = usersRepository.getUsers()

}


