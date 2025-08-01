package io.github.zyfy29.vote48.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.zyfy29.vote48.data.VoteApiClient
import io.github.zyfy29.vote48.data.VoteApiService
import io.github.zyfy29.vote48.schema.IdolItem
import io.github.zyfy29.vote48.state.IdolFanUiState
import io.github.zyfy29.vote48.state.IdolUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val voteApiService: VoteApiService = VoteApiClient.apiService
) : ViewModel() {
    private val _idolUiState = MutableStateFlow(IdolUiState())
    val idolUiState: StateFlow<IdolUiState> = _idolUiState

    private val _idolFanUiState = MutableStateFlow(IdolFanUiState())
    val idolFanUiState: StateFlow<IdolFanUiState> = _idolFanUiState

    fun fetchIdolList() = viewModelScope.launch {
        _idolUiState.value = _idolUiState.value.copy(isLoading = true, errorMessage = null)
        try {
            val res = voteApiService.getIdolList()
            _idolUiState.value = _idolUiState.value.copy(
                idols = res.data!!.list,
            )
        } catch (e: Exception) {
            _idolUiState.value = _idolUiState.value.copy(errorMessage = e.message ?: "error with no message")
        } finally {
            _idolUiState.value = _idolUiState.value.copy(isLoading = false)
        }
    }

    fun fetchIdolFanList(idolItem: IdolItem) = viewModelScope.launch {
        _idolFanUiState.value = _idolFanUiState.value.copy(idolItem = idolItem, isLoading = true, errorMessage = null)
        try {
            val res = voteApiService.getIdolFanList(userId = idolItem.cyId)
            _idolFanUiState.value = _idolFanUiState.value.copy(
                fans = res.data!!.list!!,
            )
        } catch (e: Exception) {
            _idolFanUiState.value = _idolFanUiState.value.copy(errorMessage = e.message ?: "error with no message")
        } finally {
            _idolFanUiState.value = _idolFanUiState.value.copy(isLoading = false)
        }
    }

    init {
        fetchIdolList()
    }
}