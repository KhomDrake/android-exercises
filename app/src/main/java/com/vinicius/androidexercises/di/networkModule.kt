package com.vinicius.androidexercises.di

import com.vinicius.androidexercises.remote.network.ClientBuilder
import com.vinicius.androidexercises.remote.network.GithubApi
import com.vinicius.androidexercises.remote.network.RetrofitBuilder
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { ClientBuilder.build() }
    single { ClientBuilder.build() }
    single { RetrofitBuilder.build(get()) }
    single { get<Retrofit>().create(GithubApi::class.java) }
}