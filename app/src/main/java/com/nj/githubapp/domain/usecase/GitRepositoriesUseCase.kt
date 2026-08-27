package com.nj.githubapp.domain.usecase

import com.nj.githubapp.core.common.Result
import com.nj.githubapp.data.model.GitDataPayload
import com.nj.githubapp.data.model.GitResponse
import com.nj.githubapp.domain.repository.GitRepository
import javax.inject.Inject

/**
 * UseCase for fetching GitHub repositories.
 * Simplified for interview clarity: No base class, direct repository call.
 */
class GitRepositoriesUseCase @Inject constructor(
    private val repository: GitRepository
) {
    suspend operator fun invoke(query: String): Result<GitResponse> {
        return repository.getGitRepositoryData(GitDataPayload(query))
    }
}
