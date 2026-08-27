package com.nj.githubapp.core.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nj.githubapp.R
import com.nj.githubapp.core.common.Result

@Composable
fun <T> UiHandle(
    state: Result<T>,
    onErrorDismiss: () -> Unit = {},
    content: @Composable (T) -> Unit
) {
    when (state) {
        is Result.Loading -> LoadingUi()
        is Result.Success -> content(state.data)
        is Result.Error -> ErrorUi(state.message, onErrorDismiss)
    }
}

@Composable
fun LoadingUi() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorUi(errorMsg: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.text_ok))
            }
        },
        title = { Text(stringResource(R.string.text_error_occurred)) },
        text = { Text(errorMsg) }
    )
}
