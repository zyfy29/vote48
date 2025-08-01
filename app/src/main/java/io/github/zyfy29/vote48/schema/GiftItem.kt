package io.github.zyfy29.vote48.schema

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GiftItem(
    @SerialName("gift_name") val giftName: String,
    @SerialName("gift_num") val giftNum: String,
    val vote: String,
    val pnum: String
)
