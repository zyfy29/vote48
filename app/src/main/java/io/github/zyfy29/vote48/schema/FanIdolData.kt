package io.github.zyfy29.vote48.schema

import kotlinx.serialization.Serializable

@Serializable
data class FanIdolData(
    val list: List<FanIdolItem>? = null,
)

