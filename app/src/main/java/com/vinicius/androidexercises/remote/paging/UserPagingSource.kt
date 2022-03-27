package com.vinicius.androidexercises.remote.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vinicius.androidexercises.data.ui.User
import com.vinicius.androidexercises.remote.network.GithubApi

class UserPagingSource(
    private val githubApi: GithubApi,
    private val user: String,
    private val e: MutableLiveData<Throwable>
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: DEFAULT_PAGE
        return runCatching {
            val response = githubApi.searchUsersAsync(
                user, page
            )

            val nextKey = if (page * PER_PAGE >= response.totalCount) null else page + 1

            val items = response.users.map { userResponse -> User(userResponse) }

            LoadResult.Page(
                items,
                prevKey = if (page == DEFAULT_PAGE) null else page - 1,
                nextKey = if(response.totalCount == 0) null else nextKey
            )
        }.getOrElse {
            if(page == DEFAULT_PAGE)
                e.postValue(it)
            LoadResult.Error(it)
        }
    }

}