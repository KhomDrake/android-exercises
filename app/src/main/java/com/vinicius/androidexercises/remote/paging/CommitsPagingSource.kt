package com.vinicius.androidexercises.remote.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vinicius.androidexercises.data.ui.CommitBody
import com.vinicius.androidexercises.remote.network.GithubApi

const val PER_PAGE = 10

class CommitsPagingSource(
    private val githubApi: GithubApi,
    private val q: String,
    private val firstPage: MutableLiveData<Throwable>
) : PagingSource<Int, CommitBody>() {
    override fun getRefreshKey(state: PagingState<Int, CommitBody>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommitBody> {
        val g = params.key ?: DEFAULT_PAGE
        return runCatching {
            val response = githubApi.searchCommitsAsync(
                q, g, PER_PAGE
            )

            val i = if (g * PER_PAGE >= response.totalCount) null else g + 1

            val commits = response.items.map { commit -> CommitBody(commit) }

            LoadResult.Page(
                commits,
                prevKey = if (g == DEFAULT_PAGE) null else g - 1,
                nextKey = if(response.totalCount == 0) null else i
            )
        }.getOrElse {
            if(g == DEFAULT_PAGE)
                firstPage.postValue(it)

            LoadResult.Error(it)
        }
    }
}