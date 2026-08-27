package com.nj.githubapp.data.model

import com.google.gson.annotations.SerializedName

data class GitDataPayload(
   val filter:String
)
data class GitResponse(
    @SerializedName("items")
    val items: List<Item>,
)


data class Item(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("language")
    val language: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks")
    val forks: Int,
    @SerializedName("open_issues")
    val openIssues: Int,
    @SerializedName("default_branch")
    val defaultBranch: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val login: String,
)