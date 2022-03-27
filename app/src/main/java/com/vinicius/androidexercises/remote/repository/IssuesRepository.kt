package com.vinicius.androidexercises.remote.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vinicius.androidexercises.data.ui.Issue
import com.vinicius.androidexercises.remote.network.GithubApi
import com.vinicius.androidexercises.remote.paging.IssuesPagingSource
import kotlinx.coroutines.flow.Flow

class IssuesRepository(private val githubApi: GithubApi) {

    private val pagingConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false,
        prefetchDistance = 3
    )

    fun issuesPaging(
        repo: String,
        fPE: MutableLiveData<Throwable>
    ) : Flow<PagingData<Issue>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { IssuesPagingSource(githubApi, repo, fPE) }
        ).flow
    }

}