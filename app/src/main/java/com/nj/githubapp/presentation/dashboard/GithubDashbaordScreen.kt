package com.nj.githubapp.presentation.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nj.githubapp.core.utils.UiHandle

@Composable
fun GithubDashboardScreen(viewModel: GithubDashboardViewModel = hiltViewModel())
{
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UiHandle(
        state = uiState,
        content = {
            Screen()
        }
    )
}
@Composable
private fun Screen()
{

}