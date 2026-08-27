package com.nj.githubapp.data.remote

import com.nj.githubapp.data.model.GitResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitDataSource {
    @GET("search/repositories")
    suspend fun getGitData(
        @Query("q") query: String
    ): Response<GitResponse>
}
