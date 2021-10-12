package com.example.firstcomposeapp.base.models

data class ServerCrash(
    val exception: String,
    val `file`: String,
    val line: Int,
    val message: String,
    val trace: List<Trace>,
) {
    data class Trace(
        val `class`: String,
        val `file`: String,
        val function: String,
        val line: Int,
        val type: String,
    )
}