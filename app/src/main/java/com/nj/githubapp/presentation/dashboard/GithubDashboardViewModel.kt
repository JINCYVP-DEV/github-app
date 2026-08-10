package com.nj.githubapp.presentation.dashboard

import androidx.lifecycle.ViewModel
import com.nj.githubapp.core.utils.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class GithubDashboardViewModel @Inject constructor(): ViewModel() {
    val _uiState : MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Nothing)
    val uiState : StateFlow<ScreenState> = _uiState.asStateFlow()
}