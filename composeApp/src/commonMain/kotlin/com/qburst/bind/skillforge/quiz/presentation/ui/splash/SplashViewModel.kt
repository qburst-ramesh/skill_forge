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
                    accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzU1MTUzOTc4LCJpYXQiOjE3NTM4NjAxMzUsImp0aSI6IjY1OTU1MzQ2ZWFkMzQ0ZWQ4OWVmNmE0YTUxOTY0MmI2IiwidXNlcl9pZCI6MjF9.ap_Qp3OG5uC0WirLgHniYPi1z5p9qlci41e2MaMxUWo",
                    refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTc1NTc1ODc3OCwiaWF0IjoxNzU0NTQ5MTc4LCJqdGkiOiJiZDljNGI0ZGQ5MWI0ZGZiODUzYmVkYzk3MmNjY2FlYiIsInVzZXJfaWQiOjIxfQ.x-m2y2EeEuap3oO9g2hy52A3wWPBNe1aGtroQ-h6tjc"
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