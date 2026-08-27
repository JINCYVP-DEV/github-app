package com.nj.githubapp.data.repository

import com.nj.githubapp.core.common.Result
import com.nj.githubapp.core.network.NetworkUtils
import com.nj.githubapp.data.model.GitDataPayload
import com.nj.githubapp.data.model.GitResponse
import com.nj.githubapp.data.remote.GitDataSource
import com.nj.githubapp.domain.repository.GitRepository
import javax.inject.Inject
import java.io.IOException

class GitRepositoryImpl @Inject constructor(
    private val dataSource: GitDataSource,
    private val networkUtils: NetworkUtils
) : GitRepository {

    override suspend fun getGitRepositoryData(payload: GitDataPayload): Result<GitResponse> {
        if (!networkUtils.isNetworkAvailable()) {
            return Result.Error("No internet connection")
        }

        return try {
            val response = dataSource.getGitData(payload.filter)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) Result.Success(body)
                else Result.Error("Empty response")
            } else {
                Result.Error("Error: ${response.code()} ${response.message()}")
            }
        } catch (e: IOException) {
            Result.Error("Network failure. Please check your connection.")
        } catch (e: Exception) {
            Result.Error("An unexpected error occurred: ${e.localizedMessage}")
        }
    }
}
