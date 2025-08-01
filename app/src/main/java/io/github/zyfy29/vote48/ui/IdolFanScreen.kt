package io.github.zyfy29.vote48.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.zyfy29.vote48.schema.IdolFanItem
import io.github.zyfy29.vote48.schema.IdolItem
import io.github.zyfy29.vote48.state.IdolFanUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdolFanScreen(
    uiState: IdolFanUiState,
    onViewDetail: (IdolFanItem) -> Unit,
    onRefresh: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("${uiState.idolItem.nickname}'s Fan Rankings") },
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        if (uiState.errorMessage != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.errorMessage,
                    modifier = Modifier.padding(24.dp),
                )
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(uiState.fans) { idx, fan ->
                IdolFanCard(rank = idx + 1,onViewDetail = onViewDetail, idolFanItem = fan)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IdolFanScreenPreview() {
    val sampleFans = listOf(
        IdolFanItem(
            userId = "1",
            userName = "ファン太郎",
            vote = "100"
        ),
        IdolFanItem(
            userId = "2",
            userName = "ファン花子",
            vote = "200"
        ),
        IdolFanItem(
            userId = "3",
            userName = "熱心なファン",
            vote = "150.5"
        )
    )
    IdolFanScreen(uiState = IdolFanUiState(idolItem = IdolItem(nickname = "アイドルA"), fans = sampleFans),
        onViewDetail = {},
        onRefresh = {})
}

@Preview(showBackground = true)
@Composable
fun IdolFanScreenPreviewLoading() {
    IdolFanScreen(uiState = IdolFanUiState(isLoading = true),
        onViewDetail = {},
        onRefresh = {})
}

@Preview(showBackground = true)
@Composable
fun IdolFanScreenPreviewError() {
    IdolFanScreen(
        uiState = IdolFanUiState(errorMessage = "ファンデータの読み込みに失敗しました"),
        onViewDetail = {},
        onRefresh = {}
    )
}