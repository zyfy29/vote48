package io.github.zyfy29.vote48.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import io.github.zyfy29.vote48.schema.IdolItem

@Composable
fun IdolCard(idolItem: IdolItem, onViewDetail: (IdolItem) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = idolItem.rank,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )

            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .background(
                        color = idolItem.color?.takeIf { it.isNotEmpty() }?.let {
                            Color("#$it".toColorInt())
                        } ?: Color.Gray,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${idolItem.gname} ${idolItem.tname}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Nickname
            Text(
                text = idolItem.nickname,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .weight(1.5f)
                    .padding(end = 8.dp)
            )

            // Vote count
            Text(
                text = idolItem.vote.substringBefore("."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            // Detail button
            IconButton(
                onClick = { onViewDetail(idolItem) }
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "詳細を見る",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun IdolCardPreview() {
    val sampleIdol = IdolItem(
        cyId = "12345",
        nickname = "朱怡欣",
        vote = "218398.80",
        gname = "GNZ",
        tname = "Z",
        rank = "1",
        color = "ea627c"
    )
    IdolCard(idolItem = sampleIdol, onViewDetail = {})
}

@Preview
@Composable
fun IdolCardPreviewBorder() {
    val sampleIdol = IdolItem(
        cyId = "12345",
        nickname = "四个文字",
        vote = "218398.80",
        gname = "AAA",
        tname = "BB",
        rank = "111",
        color = "ea627c"
    )
    IdolCard(idolItem = sampleIdol, onViewDetail = {})
}