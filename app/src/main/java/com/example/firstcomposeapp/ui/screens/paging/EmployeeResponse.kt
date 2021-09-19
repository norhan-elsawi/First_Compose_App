package com.example.firstcomposeapp.ui.screens.paging

data class EmployeeResponse(
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: List<Employee>,
    var support: Support,
)

data class Employee(
    var id: Int,
    var email: String,
    var first_name: String,
    var last_name: String,
    var avatar: String,
)

data class Support(
    var url: String,
    var text: String,
)