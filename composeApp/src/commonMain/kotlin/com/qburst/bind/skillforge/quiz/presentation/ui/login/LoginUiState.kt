package com.qburst.bind.skillforge.quiz.presentation.ui.login

import com.qburst.bind.skillforge.quiz.domain.model.LoginData

data class LoginUiState(
    val loading: Boolean = false,
    val error: String = "",
    val isLoggedIn: Boolean = false,
    val userInfo: LoginData? = null
)