package com.vinicius.androidexercises.ui.detail.information

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vinicius.androidexercises.data.ui.Issue
import com.vinicius.androidexercises.data.ui.Repository
import com.vinicius.androidexercises.remote.repository.CommitsRepository
import com.vinicius.androidexercises.ui.PagingViewModel
import kotlinx.coroutines.flow.catch

class CommitsViewModel(private val commitsRepository: CommitsRepository) : PagingViewModel() {

    var repo: String = ""
    var language: String = ""

    fun commitsPaging() = commitsRepository.commitsPaging(
        repo, language, _error
    )
    .catch {
        PagingData.empty<Issue>()
    }
    .cachedIn(viewModelScope)

}