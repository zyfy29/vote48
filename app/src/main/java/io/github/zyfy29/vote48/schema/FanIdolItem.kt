package io.github.zyfy29.vote48.schema

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FanIdolItem(
    val vote: String,
    @SerialName("cy_name") val idolName: String,
)
