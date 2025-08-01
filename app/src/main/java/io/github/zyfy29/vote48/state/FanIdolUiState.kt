package io.github.zyfy29.vote48.state

import io.github.zyfy29.vote48.schema.FanIdolItem
import io.github.zyfy29.vote48.schema.IdolFanItem

data class FanIdolUiState(
    val fan: IdolFanItem = IdolFanItem(),
    val fanIdolList: List<FanIdolItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
