package io.github.zyfy29.vote48.schema

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val error: String? = null,
    val msg: String? = null,
    val data: T? = null
)