package com.qburst.bind.skillforge.quiz.presentation.ui.splash

data class SplashUiState(
    val token: String?,
    val loading: Boolean = true,
    val refreshToken: String?
)
