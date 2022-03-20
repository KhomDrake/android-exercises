package com.vinicius.androidexercises.remote.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vinicius.androidexercises.data.network.UserResponse
import com.vinicius.androidexercises.remote.network.GithubApi

class UserPagingSource(
    private val githubApi: GithubApi,
    private val user: String,
    private val errorFirstPage: MutableLiveData<Throwable>
) : PagingSource<Int, UserResponse>() {

    override fun getRefreshKey(state: PagingState<Int, UserResponse>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        val page = params.key ?: DEFAULT_PAGE
        return runCatching {
            val response = githubApi.searchUsersAsync(
                user, page
            )

            val nextKey = if (page * PER_PAGE >= response.totalCount) null else page + 1

            LoadResult.Page(
                response.users,
                prevKey = if (page == DEFAULT_PAGE) null else page - 1,
                nextKey = if(response.totalCount == 0) null else nextKey
            )
        }.getOrElse {
            if(page == DEFAULT_PAGE)
                errorFirstPage.postValue(it)
            LoadResult.Error(it)
        }
    }

}