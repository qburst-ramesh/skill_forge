package com.qburst.bind.skillforge.quiz.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qburst.bind.skillforge.quiz.domain.usecase.login.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnGoogleLoginClick -> {
                viewModelScope.launch {
                    loginUseCase.saveAccessAndRefreshToken(
                        accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzU0NDY0OTM1LCJpYXQiOjE3NTM3OTQ2MzQsImp0aSI6IjMyMTZiZTI3YWRjYTRmMmZhNjYzNzIwMTk1ZmY0MmM5IiwidXNlcl9pZCI6MjF9.kQq1tFypqycVKCfJjztjujFiQJIi8SSYhv5AFzsvoL4",
                        refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTc1NTA2OTczNSwiaWF0IjoxNzUzODYwMTM1LCJqdGkiOiI3ZTAxYjgwNDE1N2Q0YjUyYTIxMmQxOWJmZDgzYWNlYSIsInVzZXJfaWQiOjIxfQ.2-1smO2R7_Jg-e61di2z7qV_7-yHXcAwEhvCXLXdPrM"
                    )
                }
//                loginWithGoogle()
            }

            is LoginUiEvent.OnLoginError -> {
                // Handled in the OnOAuthTokenReceived
            }

            is LoginUiEvent.OnLoginSuccess -> {
                event.loginData?.let {
                    viewModelScope.launch {
                        loginUseCase.saveAccessAndRefreshToken(
                            accessToken = it.accessToken,
                            refreshToken = it.refreshToken
                        )
                        println("Token ${it.accessToken}")
                        println("Refresh Token ${it.refreshToken}")
                        loginUseCase.saveUserLogin(isLoggedIn = true)
                    }
                }
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    isLoggedIn = true,
                    userInfo = event.loginData
                )
            }

            is LoginUiEvent.OnOAuthTokenReceived -> {
                event.userData?.token.let { authToken ->
                    println(authToken)
                    _uiState.value = _uiState.value.copy(loading = true, error = "")
                    viewModelScope.launch {
                        if (event.userData != null) {
                            val result = loginUseCase.login(googleUser = event.userData)
                            if (result.isSuccess) {
                                onEvent(event = LoginUiEvent.OnLoginSuccess(loginData = result.getOrNull()))
                            } else {
                                _uiState.value = _uiState.value.copy(
                                    loading = false,
                                    error = result.exceptionOrNull()?.message
                                        ?: "Server login failed"
                                )
                            }
                        } else {
                            _uiState.value =
                                _uiState.value.copy(loading = false, error = "Google login failed")
                        }
                    }
                }
            }
        }
    }

    private fun loginWithGoogle() {
        _uiState.value = _uiState.value.copy(loading = false, isLoggedIn = false, error = "")
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = false)
        }
    }
}
