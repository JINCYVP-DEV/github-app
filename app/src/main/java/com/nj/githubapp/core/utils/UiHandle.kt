package com.nj.githubapp.core.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nj.githubapp.R

@Composable
fun UiHandle(state: ScreenState,content: @Composable () -> Unit)
{
    when(state)
    {
        is ScreenState.Loading -> {
            //Show Loading
            LoadingUi()
        }
        is ScreenState.Success -> {
            //Show Success
            content()
        }
        is ScreenState.Error -> {
            //Show Error
            ErrorUi(state.message)
        }
    }
}
@Composable
fun LoadingUi()
{
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        CircularProgressIndicator()
    }
}
@Composable
fun ErrorUi(errorMsg: String)
{
  AlertDialog(onDismissRequest = {

  }, confirmButton = {
        Text(stringResource(R.string.text_ok))
  }, text = {
      Text(errorMsg)
  }, title = {
      Text(stringResource(R.string.text_error_occurred))
  })
}