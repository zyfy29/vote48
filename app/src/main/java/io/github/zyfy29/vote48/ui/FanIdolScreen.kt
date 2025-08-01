package io.github.zyfy29.vote48.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.zyfy29.vote48.schema.FanIdolItem
import io.github.zyfy29.vote48.schema.IdolFanItem
import io.github.zyfy29.vote48.state.FanIdolUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FanIdolScreen(uiState: FanIdolUiState, onRefresh: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("${uiState.fan.userName}'s Voted Rankings") },
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
            item {
                val totalVotes = uiState.fanIdolList.sumOf { fanIdolItem ->
                    try {
                        fanIdolItem.vote.toDouble()
                    } catch (e: NumberFormatException) {
                        0.0
                    }
                }
                val totalVotesText = if (totalVotes % 1 == 0.0) {
                    "Total: ${totalVotes.toInt()}"
                } else {
                    "Total: ${String.format("%.1f", totalVotes)}"
                }

                Text(
                    text = totalVotesText,
//                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
            itemsIndexed(uiState.fanIdolList) { idx, idol ->
                Log.d("FanIdolScreen", "Rendering idol: ${idol.idolName}")
                FanIdolCard(rank = idx + 1, fanIdolItem = idol)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FanIdolScreenPreview() {
    val sampleIdols = listOf(
        FanIdolItem(
            idolName = "山田花子",
            vote = "100"
        ),
        FanIdolItem(
            idolName = "田中美咲",
            vote = "200"
        ),
        FanIdolItem(
            idolName = "佐藤愛美",
            vote = "150.5"
        )
    )
    FanIdolScreen(
        uiState = FanIdolUiState(
            fan = IdolFanItem(userName = "ファン太郎"),
            fanIdolList = sampleIdols
        ),
        onRefresh = {}
    )
}

@Preview(showBackground = true)
@Composable
fun FanIdolScreenPreviewLoading() {
    FanIdolScreen(uiState = FanIdolUiState(isLoading = true), onRefresh = {})
}

@Preview(showBackground = true)
@Composable
fun FanIdolScreenPreviewError() {
    FanIdolScreen(
        uiState = FanIdolUiState(errorMessage = "アイドルデータの読み込みに失敗しました"),
        onRefresh = {}
    )
}