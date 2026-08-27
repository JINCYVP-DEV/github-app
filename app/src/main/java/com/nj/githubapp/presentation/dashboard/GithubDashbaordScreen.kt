package com.nj.githubapp.presentation.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.nj.githubapp.R
import com.nj.githubapp.core.utils.UiHandle
import com.nj.githubapp.data.model.Item

@Composable
fun GithubDashboardScreen(
    padding: PaddingValues,
    viewModel: GithubDashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(padding)) {
        UiHandle(
            state = uiState,
            content = { items ->
                RepositoryList(items)
            }
        )
    }
}

@Composable
private fun RepositoryList(items: List<Item>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items.size) { index ->
            RepositoryItem(item = items[index])
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_medium)),
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}

@Composable
fun RepositoryItem(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.margin_medium)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_regular)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.owner.avatarUrl,
                contentDescription = null,
                modifier = Modifier.size(50.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.margin_medium)))
            Column {
                Text(
                    text = item.fullName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.description ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(modifier = Modifier.padding(top = 4.dp)) {
                    Text(text = "⭐ ${item.stargazersCount}", style = MaterialTheme.typography.labelSmall)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = item.language.orEmpty(), style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}
