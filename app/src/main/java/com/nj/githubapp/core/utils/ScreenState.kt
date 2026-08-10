package com.nj.githubapp.core.utils

sealed class ScreenState {

    object Nothing : ScreenState()
    object Loading : ScreenState()
    data class Success(val data: Any) : ScreenState()
    data class Error(val message: String) : ScreenState()
}