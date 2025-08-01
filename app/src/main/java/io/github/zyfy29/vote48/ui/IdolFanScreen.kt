package io.github.zyfy29.vote48.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun IdolFanScreen() {
    Text(
        text = "Group Screen",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}