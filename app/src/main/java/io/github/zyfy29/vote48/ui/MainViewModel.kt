package io.github.zyfy29.vote48.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.zyfy29.vote48.data.VoteApiClient
import io.github.zyfy29.vote48.data.VoteApiService
import io.github.zyfy29.vote48.schema.IdolUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val voteApiService: VoteApiService = VoteApiClient.apiService
) : ViewModel() {
    private val _idolUiState = MutableStateFlow(IdolUiState())
    val idolUiState: StateFlow<IdolUiState> = _idolUiState
    fun fetchIdolList() = viewModelScope.launch {
        Log.d("MainViewModel", "Fetching idol list")
        _idolUiState.value = _idolUiState.value.copy(isLoading = true, errorMessage = null)
        try {
            val res = voteApiService.getIdolList()
            _idolUiState.value = _idolUiState.value.copy(
                idols = res.data!!.list,
                isLoading = false,
                errorMessage = null
            )
        } catch (e: Exception) {
            _idolUiState.value = _idolUiState.value.copy(errorMessage = e.message ?: "error with no message")
        } finally {
            _idolUiState.value = _idolUiState.value.copy(isLoading = false)
        }
    }

    init {
        fetchIdolList()
    }
}