package com.vinicius.androidexercises.data.network

import com.google.gson.annotations.SerializedName

class RepositoriesResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<RepositoryResponse>
)

class RepositoryResponse(
    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val owner: OwnerRepositoryResponse,
    val description: String?,
    val isFork: Boolean,
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("default_branch")
    val defaultBranch: String,
    val score: Double,
    val watchers: Int,
    @SerializedName("open_issues")
    val openIssues: Int,
    val language: String?,
    @SerializedName("stargazers_count")
    val stars: Int,
    val forks: Int,
    @SerializedName("html_url")
    val url: String,
    val license: LicenseResponse?
)

class LicenseResponse(
    val url: String?,
    val key: String,
    val name: String
)

class OwnerRepositoryResponse(
    val login: String,
    val idOwner: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val type: String,
    @SerializedName("html_url")
    val url: String?
)