package com.qburst.bind.skillforge.quiz.presentation.ui.login

import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser

sealed class LoginUiEvent {
    object OnGoogleLoginClick : LoginUiEvent()
    data class OnOAuthTokenReceived(val userData: GoogleUser?) : LoginUiEvent()
    object OnLoginSuccess : LoginUiEvent()
    data class OnLoginError(val error: String) : LoginUiEvent()
}