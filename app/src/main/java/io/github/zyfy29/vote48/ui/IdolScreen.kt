package io.github.zyfy29.vote48.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.zyfy29.vote48.schema.IdolItem
import io.github.zyfy29.vote48.schema.IdolUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdolScreen(uiState: IdolUiState, onRefresh: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Idol Rankings") },
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
            items(uiState.idols) { idol ->
                Log.d("IdolScreen", "Rendering idol: ${idol.nickname}")
                IdolCard(idolItem = idol)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IdolScreenPreview() {
    val sampleIdols = listOf(
        IdolItem(
            cyId = "12345",
            nickname = "朱怡欣",
            vote = "218398.80",
            gname = "GNZ",
            tname = "Z",
            rank = "1",
            color = "ea627c"
        ),
        IdolItem(
            cyId = "67890",
            nickname = "柏欣妤",
            vote = "212525.40",
            gname = "SNH",
            tname = "N2",
            rank = "2",
            color = "ae86bb"
        )
    )
    IdolScreen(uiState = IdolUiState(idols = sampleIdols), onRefresh = {})
}


@Preview(showBackground = true)
@Composable
fun IdolScreenPreviewLoading() {
    IdolScreen(uiState = IdolUiState(isLoading = true), onRefresh = {})
}

@Preview(showBackground = true)
@Composable
fun IdolScreenPreviewError() {
    IdolScreen(
        uiState = IdolUiState(errorMessage = "I am an error message"),
        onRefresh = {}
    )
}