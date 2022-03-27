package com.vinicius.androidexercises.di

import com.vinicius.androidexercises.ui.detail.information.CommitsViewModel
import com.vinicius.androidexercises.ui.home.HomeViewModel
import com.vinicius.androidexercises.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { CommitsViewModel(get()) }
}