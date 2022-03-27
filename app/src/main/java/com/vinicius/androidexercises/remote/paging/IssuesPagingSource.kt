package com.vinicius.androidexercises.remote.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vinicius.androidexercises.data.ui.Issue
import com.vinicius.androidexercises.remote.network.GithubApi

class IssuesPagingSource(
    private val githubApi: GithubApi,
    private val repo: String,
    private val fPE: MutableLiveData<Throwable>
) : PagingSource<Int, Issue>() {

    override fun getRefreshKey(state: PagingState<Int, Issue>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Issue> {
        val p = params.key ?: DEFAULT_PAGE
        return runCatching {
            val response = githubApi.searchIssuesAsync(
                "repo:$repo", p, PER_PAGE
            )

            val nK = if (p * PER_PAGE >= response.totalCount) null else p + 1

            val items = response.items.map { issue -> Issue(issue) }

            LoadResult.Page(
                items,
                prevKey = if (p == DEFAULT_PAGE) null else p - 1,
                nextKey = if(response.totalCount == 0) null else nK
            )
        }.getOrElse {
            if(p == DEFAULT_PAGE)
                fPE.postValue(it)

            LoadResult.Error(it)
        }
    }

}