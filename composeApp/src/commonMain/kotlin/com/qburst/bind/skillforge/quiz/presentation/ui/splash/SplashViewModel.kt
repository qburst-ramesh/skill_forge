package com.qburst.bind.skillforge.quiz.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qburst.bind.skillforge.quiz.domain.usecase.login.LoginUseCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashUiState(token = null, refreshToken = null))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val token = loginUseCase.getAccessToken()
            print("Splash Viewmodel Token ${token}")
            Napier.e(
                message = "Splash Viewmodel Token ${token}",
                tag = "SplashScreen"
            )

            viewModelScope.launch {
                loginUseCase.saveAccessAndRefreshToken(
                    accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzU0NDY0OTM1LCJpYXQiOjE3NTM3OTQ2MzQsImp0aSI6IjMyMTZiZTI3YWRjYTRmMmZhNjYzNzIwMTk1ZmY0MmM5IiwidXNlcl9pZCI6MjF9.kQq1tFypqycVKCfJjztjujFiQJIi8SSYhv5AFzsvoL4",
                    refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTc1NTA2OTczNSwiaWF0IjoxNzUzODYwMTM1LCJqdGkiOiI3ZTAxYjgwNDE1N2Q0YjUyYTIxMmQxOWJmZDgzYWNlYSIsInVzZXJfaWQiOjIxfQ.2-1smO2R7_Jg-e61di2z7qV_7-yHXcAwEhvCXLXdPrM"
                )
            }

            _uiState.value = SplashUiState(
                token = loginUseCase.getAccessToken(),
                loading = false,
                refreshToken = loginUseCase.getRefreshedToken()
            )
        }
    }
}