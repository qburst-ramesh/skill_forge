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

    var token: String? = ""

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnGoogleLoginClick -> {
                loginWithGoogle()
            }

            is LoginUiEvent.OnLoginError -> {
                // Handled in the OnOAuthTokenReceived
            }

            is LoginUiEvent.OnLoginSuccess -> {
                _uiState.value = _uiState.value.copy(loading = false, isLoggedIn = true, error = "")
            }

            is LoginUiEvent.OnOAuthTokenReceived -> {
                println(event.userData?.token)
                token = event.userData?.token
                _uiState.value = _uiState.value.copy(loading = true, error = "")
                viewModelScope.launch {
                    if (event.userData != null) {
                        val result = loginUseCase.login(googleUser = event.userData)
                        if (result.isSuccess) {
                            _uiState.value = _uiState.value.copy(
                                loading = false,
                                isLoggedIn = true,
                                userInfo = result.getOrNull()
                            )
                        } else {
                            _uiState.value = _uiState.value.copy(
                                loading = false,
                                error = result.exceptionOrNull()?.message ?: "Server login failed"
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

    private fun loginWithGoogle() {
        _uiState.value = _uiState.value.copy(loading = false, isLoggedIn = false, error = "")
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = false)
        }
    }
}