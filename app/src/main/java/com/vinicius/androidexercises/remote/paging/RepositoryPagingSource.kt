package com.vinicius.androidexercises.remote.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vinicius.androidexercises.data.ui.Repository
import com.vinicius.androidexercises.remote.network.GithubApi

const val DEFAULT_PAGE = 1

class RepositoryPagingSource(
    private val githubApi: GithubApi,
    private val language: String,
    private val e: MutableLiveData<Throwable>
) :
    PagingSource<Int, Repository>() {

    override fun getRefreshKey(state: PagingState<Int, Repository>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val a = params.key ?: DEFAULT_PAGE
        return runCatching {
            val response = githubApi.searchReposAsync(
                "language:kotlin", a
            )

            val k = if (a * PER_PAGE >= response.totalCount) null else a + 1

            val items = response.items.map { repositoryResponse -> Repository(repositoryResponse) }

            LoadResult.Page(
                items,
                prevKey = if (a == DEFAULT_PAGE) null else a - 1,
                nextKey = if(response.totalCount == 0) null else k
            )
        }.getOrElse {
            if(a == DEFAULT_PAGE)
                e.postValue(it)

            LoadResult.Error(it)
        }
    }
}
