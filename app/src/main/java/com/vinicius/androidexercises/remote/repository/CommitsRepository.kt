package com.vinicius.androidexercises.remote.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vinicius.androidexercises.data.ui.CommitBody
import com.vinicius.androidexercises.remote.network.GithubApi
import com.vinicius.androidexercises.remote.paging.CommitsPagingSource
import kotlinx.coroutines.flow.Flow

class CommitsRepository(private val githubApi: GithubApi) {

    private val pagingConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false,
        prefetchDistance = 3
    )

    fun commitsPaging(
        repo: String,
        language: String,
        firstPage: MutableLiveData<Throwable>
    ) : Flow<PagingData<CommitBody>> {
        val q = "repo:$repo+$language"
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CommitsPagingSource(githubApi, q, firstPage) }
        ).flow
    }

}