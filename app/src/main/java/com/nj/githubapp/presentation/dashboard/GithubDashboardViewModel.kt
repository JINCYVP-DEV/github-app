package com.nj.githubapp.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nj.githubapp.core.common.Result
import com.nj.githubapp.data.model.Item
import com.nj.githubapp.domain.usecase.GitRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubDashboardViewModel @Inject constructor(
    private val useCase: GitRepositoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<List<Item>>>(Result.Loading)
    val uiState: StateFlow<Result<List<Item>>> = _uiState.asStateFlow()

    init {
        searchRepositories("android")
    }

    fun searchRepositories(query: String) {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            val result = useCase(query)
            _uiState.value = when (result) {
                is Result.Success -> Result.Success(result.data.items)
                is Result.Error -> Result.Error(result.message)
                is Result.Loading -> Result.Loading
            }
        }
    }
}
