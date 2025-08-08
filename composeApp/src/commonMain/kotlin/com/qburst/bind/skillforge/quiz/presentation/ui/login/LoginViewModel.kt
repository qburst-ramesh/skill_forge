package com.qburst.bind.skillforge.quiz.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qburst.bind.skillforge.quiz.domain.usecase.login.LoginUseCase
import io.github.aakira.napier.Napier
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
                loginWithGoogle()
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
                    Napier.d(message = authToken.toString())
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
