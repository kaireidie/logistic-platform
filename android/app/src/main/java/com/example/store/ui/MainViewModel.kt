package com.example.store.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.api.HealthCheckApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

sealed interface BackendStatus {
    object Loading : BackendStatus
    object Up : BackendStatus
    data class Down(val message: String) : BackendStatus
}

class MainViewModel(private val api: HealthCheckApi) : ViewModel() {

    private val _status = MutableStateFlow<BackendStatus>(BackendStatus.Loading)
    val status: StateFlow<BackendStatus> = _status.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private var pollingJob: Job? = null

    init {
        startPolling()
    }

    private fun startPolling() {
        pollingJob?.cancel()
        pollingJob = viewModelScope.launch {
            while (isActive) {
                checkBackend()
                delay(30_000) // 30 секунд
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            checkBackend()
            _isRefreshing.value = false
            startPolling()
        }
    }

    private suspend fun checkBackend() {
        try {
            val response = api.checkHealth()
            if (response.isSuccessful) {
                _status.value = BackendStatus.Up
            } else {
                _status.value = BackendStatus.Down("Код ошибки: ${response.code()}")
            }
        } catch (e: Exception) {
            _status.value = BackendStatus.Down("Сервер недоступен")
        }
    }
}