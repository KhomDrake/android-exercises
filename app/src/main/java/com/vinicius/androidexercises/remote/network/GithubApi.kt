package com.vinicius.androidexercises.remote.network

import com.vinicius.androidexercises.data.network.RepositoriesResponse
import com.vinicius.androidexercises.data.network.CommitsResponse
import com.vinicius.androidexercises.data.network.UsersResponse
import com.vinicius.androidexercises.data.network.IssuesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val SORT_DEFAULT = "stars"

interface GithubApi {

    @GET("repositories")
    suspend fun searchReposAsync(
        @Query("q") language: String,
        @Query("page") page: Int,
        @Query("sort") sort: String = SORT_DEFAULT
    ) : RepositoriesResponse

    @GET("commits")
    @Headers("Accept: application/vnd.github.cloak-preview+json")
    suspend fun searchCommitsAsync(
        @Query("q", encoded = true) repository: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : CommitsResponse

    @GET("issues")
    @Headers("Accept: application/vnd.github.v3+json")
    suspend fun searchIssuesAsync(
        @Query("q") repository: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : IssuesResponse

    @GET("users")
    @Headers("Accept: application/vnd.github.v3+json")
    suspend fun searchUsersAsync(
        @Query("q") users: String,
        @Query("page") page: Int
    ) : UsersResponse

}
