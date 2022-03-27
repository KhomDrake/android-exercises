package com.vinicius.androidexercises.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.vinicius.androidexercises.data.ui.Repository
import com.vinicius.androidexercises.data.ui.User
import com.vinicius.androidexercises.remote.repository.UsersRepository
import com.vinicius.androidexercises.ui.PagingViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class SearchViewModel(private val repository: UsersRepository): PagingViewModel() {

    private var user = ""

    fun setUser(newUser: String) {
        user = newUser
    }

    fun searchUser() =
        repository.searchUser(user, _error)
            .catch {
                PagingData.empty<User>()
            }
            .cachedIn(viewModelScope)

}
