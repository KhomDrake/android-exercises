package com.vinicius.androidexercises.ui.detail.information

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vinicius.androidexercises.data.ui.Issue
import com.vinicius.androidexercises.data.ui.Repository
import com.vinicius.androidexercises.remote.repository.IssuesRepository
import com.vinicius.androidexercises.ui.PagingViewModel
import kotlinx.coroutines.flow.catch

class IssuesViewModel(private val issuesRepository: IssuesRepository) : PagingViewModel() {

    var repo: String = ""

    fun issuesPaging() = issuesRepository.issuesPaging(repo, _error)
        .catch {
            PagingData.empty<Issue>()
        }
        .cachedIn(viewModelScope)

}