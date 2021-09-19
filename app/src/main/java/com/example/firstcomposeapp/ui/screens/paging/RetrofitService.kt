package com.example.firstcomposeapp.ui.screens.paging

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("users")
    suspend fun getUserList(@Query("page") page: Int): EmployeeResponse
}