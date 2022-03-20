package com.vinicius.androidexercises.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.vinicius.androidexercises.data.ui.User
import com.vinicius.androidexercises.remote.repository.UsersRepository
import com.vinicius.androidexercises.ui.PagingViewModel
import kotlinx.coroutines.flow.map

class SearchViewModel(private val repository: UsersRepository): PagingViewModel() {

    private var user = ""

    fun setUser(newUser: String) {
        user = newUser
    }

    fun searchUser() =
        repository.searchUser(user, _error)
            .map { it.map { userResponse -> User(userResponse) } }
            .cachedIn(viewModelScope)

}
