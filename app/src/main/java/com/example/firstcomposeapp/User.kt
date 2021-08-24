package com.example.firstcomposeapp

import kotlin.random.Random

data class User(val name: String = "custom name", val age: Int = 0) {

    companion object {
        fun getRandomList(): ArrayList<User> {
            val users: ArrayList<User> = arrayListOf()
            repeat(5) {
                users.add(User(getRandomName(), it))
            }
            return users
        }

        private val chars = listOf("a", "b", "c", "d", "e", "g", "f", "g", "n")

        private fun getRandomName(): String {
            var name = ""
            repeat(10) {
                name += chars[Random.nextInt(from = 0, until = chars.lastIndex)]
            }
            return name
        }
    }
}