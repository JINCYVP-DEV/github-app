package com.nj.githubapp.domain.repository

import com.nj.githubapp.core.common.Result
import com.nj.githubapp.data.model.GitDataPayload
import com.nj.githubapp.data.model.GitResponse

interface GitRepository {
    suspend fun getGitRepositoryData(payload: GitDataPayload): Result<GitResponse>
}