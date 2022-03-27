package com.vinicius.androidexercises.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.vinicius.androidexercises.data.ui.Repository
import com.vinicius.androidexercises.remote.repository.ReposRepository
import com.vinicius.androidexercises.ui.PagingViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private const val DEFAULT_LANGUAGE = "kotlin"

class HomeViewModel(private val repository: ReposRepository) : PagingViewModel() {

    var selectedLanguage: String = DEFAULT_LANGUAGE
        private set

    fun updateSelectedLanguage(newLanguage: String) {
        selectedLanguage = newLanguage
    }

    fun repositoriesPaging() = repository.repositoriesPaging(selectedLanguage, _error)
        .catch {
            PagingData.empty<Repository>()
        }
        .cachedIn(viewModelScope)

}
