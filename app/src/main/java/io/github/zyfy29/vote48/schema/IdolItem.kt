package io.github.zyfy29.vote48.schema

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdolItem(
    @SerialName("cy_id") val cyId: String,
    @SerialName("nickname") val nickname: String,
    @SerialName("vote") val vote: String,
    @SerialName("gname") val gname: String,
    @SerialName("tname") val tname: String,
    @SerialName("rank") val rank: String,
    @SerialName("color") val color: String?
)