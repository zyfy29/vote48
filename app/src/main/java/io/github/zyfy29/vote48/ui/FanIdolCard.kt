package io.github.zyfy29.vote48.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.zyfy29.vote48.schema.FanIdolItem

@Composable
fun FanIdolCard(rank: Int, fanIdolItem: FanIdolItem) {
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
                text = fanIdolItem.idolName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(2f)
            )
            val voteText = try {
                val voteDouble = fanIdolItem.vote.toDouble()
                if (voteDouble % 1 == 0.0) {
                    // 整数ならそのまま表示
                    String.format("%d", voteDouble.toInt())
                } else {
                    // 小数なら1桁まで四捨五入して表示
                    String.format("%.1f", voteDouble)
                }
            } catch (e: NumberFormatException) {
                fanIdolItem.vote // パースできない場合はそのまま表示
            }
            Text(
                text = voteText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFanIdolCard() {
    FanIdolCard(
        rank = 1,
        fanIdolItem = FanIdolItem(
            idolName = "山田花子",
            vote = "100.00"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFanIdolCardBorder() {
    FanIdolCard(
        rank = 500,
        fanIdolItem = FanIdolItem(
            idolName = "とても長いアイドルの名前です",
            vote = "200000.19999" // 最大8桁
        )
    )
}