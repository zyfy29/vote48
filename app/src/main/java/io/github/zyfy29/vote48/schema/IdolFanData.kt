package io.github.zyfy29.vote48.schema

import kotlinx.serialization.Serializable

@Serializable
data class IdolFanData(
    val list: List<IdolFanItem>? = null,
    val pnum: String? = null,
    val score: Double? = null,
    val rank: String? = null,
    val tips: String? = null,
    val gift: List<GiftItem>? = null
)
