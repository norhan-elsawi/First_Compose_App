package com.example.firstcomposeapp.ui.screens.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

class UserSource : PagingSource<Int, Employee>() {

    override fun getRefreshKey(state: PagingState<Int, Employee>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Employee> {
        return try {
            val nextPage = params.key ?: 1
            val userList = RetrofitClient.apiService.getUserList(page = nextPage)
            Thread.sleep(2000)
            LoadResult.Page(
                data = userList.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.data.isEmpty()) null else userList.page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}