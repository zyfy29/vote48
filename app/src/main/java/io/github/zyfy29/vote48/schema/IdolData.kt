package io.github.zyfy29.vote48.schema

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdolData(
    val list: List<IdolItem> = emptyList(),
    @SerialName("zxrank_update_time") val zxrankUpdateTime: String? = null,
    val endtime: String? = null,
    val ttips: String? = null
)