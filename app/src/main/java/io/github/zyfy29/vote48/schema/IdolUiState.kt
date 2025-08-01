package io.github.zyfy29.vote48.schema

data class IdolUiState(
    val idols: List<IdolItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
