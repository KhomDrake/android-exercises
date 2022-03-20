package com.vinicius.androidexercises.di

import com.vinicius.androidexercises.remote.repository.CommitsRepository
import com.vinicius.androidexercises.remote.repository.IssuesRepository
import com.vinicius.androidexercises.remote.repository.ReposRepository
import com.vinicius.androidexercises.remote.repository.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CommitsRepository(get()) }
    single { IssuesRepository(get()) }
    single { ReposRepository(get()) }
    single { UsersRepository(get()) }
}