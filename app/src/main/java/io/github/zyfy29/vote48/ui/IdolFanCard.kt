package io.github.zyfy29.vote48.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.zyfy29.vote48.schema.IdolFanItem

@Composable
fun IdolFanCard(rank: Int, idolFanItem: IdolFanItem) {
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
                text = rank.toString(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
            Text(
                text = idolFanItem.userName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .weight(2f)
//                    .padding(8.dp)
            )
            val voteText = try {
                val voteDouble = idolFanItem.vote.toDouble()
                if (voteDouble % 1 == 0.0) {
                    // 整数ならそのまま表示
                    idolFanItem.vote
                } else {
                    // 小数なら1桁まで四捨五入して表示
                    String.format("%.1f", voteDouble)
                }
            } catch (e: NumberFormatException) {
                idolFanItem.vote // パースできない場合はそのまま表示
            }
            Text(
                text = voteText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            IconButton(
                onClick = { /* TODO: 詳細画面への遷移 */ }
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
fun PreviewIdolFanCard() {
    IdolFanCard(
        rank = 1,
        idolFanItem = IdolFanItem(
            userName = "ファン太郎",
            vote = "100"
        )
    )
}

@Preview
@Composable
fun PreviewIdolFanCardBorder() {
    IdolFanCard(
        rank = 500,
        idolFanItem = IdolFanItem(
            userName = "このファンはとても熱心です",
            vote = "200000.19999" // 最大8桁
        )
    )
}
