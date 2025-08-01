package io.github.zyfy29.vote48.schema

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdolFanItem(
    @SerialName("user_id") val userId: String,
    @SerialName("user_name") val userName: String,
    val vote: String
)
