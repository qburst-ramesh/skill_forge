package com.qburst.bind.skillforge.quiz.presentation.ui.landing.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qburst.bind.skillforge.quiz.domain.usecase.home.HomeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(event = HomeUiEvent.OnLoadHomeData(forceRefresh = false))
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnHomeDataReceived -> {
                _uiState.value = event.homeData.let {
                    _uiState.value.copy(
                        loading = false,
                        homeData = it
                    )
                }
            }

            is HomeUiEvent.OnError -> {
                // Handle error
            }

            is HomeUiEvent.OnLoadHomeData -> {
                _uiState.value = _uiState.value.copy(loading = true)
                getHomeData(forceRefresh = event.forceRefresh)
            }
        }
    }

    private fun getHomeData(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            val result = homeUseCase.getHomeData()
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(
                    homeData = result.getOrNull(),
                    loading = false
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    error = result.exceptionOrNull()?.message ?: "Unknown error",
                    loading = false
                )
            }
        }
    }
}
