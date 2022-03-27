package com.vinicius.androidexercises.remote.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vinicius.androidexercises.data.ui.Repository
import com.vinicius.androidexercises.remote.network.GithubApi
import com.vinicius.androidexercises.remote.paging.RepositoryPagingSource
import kotlinx.coroutines.flow.Flow

const val PAGE_SIZE = 20

class ReposRepository(private val githubApi: GithubApi) {

    private val pagingConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false,
        prefetchDistance = 3
    )

    fun repositoriesPaging(
        language: String,
        e: MutableLiveData<Throwable>
    ) : Flow<PagingData<Repository>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { RepositoryPagingSource(githubApi, language, e) }
        ).flow
    }
}