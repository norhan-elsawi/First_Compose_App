package com.example.firstcomposeapp.ui.screens.usersScreen.models

const val image =
    "https://images.unsplash.com/photo-1622220838642-62263cb2141d?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80"

data class User(
    val imageUrl: String = image,
    val address: Address?,
    val email: String?,
    val id: Int?,
    val name: String?,
    val username: String?,
) {
    data class Address(
        val city: String?,
        val geo: Geo?,
        val street: String?,
        val suite: String?,
        val zipcode: String?,
    ) {
        data class Geo(
            val lat: String?,
            val lng: String?,
        )
    }
}