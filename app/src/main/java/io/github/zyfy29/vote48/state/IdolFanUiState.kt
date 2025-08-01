package io.github.zyfy29.vote48.state

import io.github.zyfy29.vote48.schema.IdolFanItem
import io.github.zyfy29.vote48.schema.IdolItem

data class IdolFanUiState(
    val idolItem: IdolItem = IdolItem(),
    val fans: List<IdolFanItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
