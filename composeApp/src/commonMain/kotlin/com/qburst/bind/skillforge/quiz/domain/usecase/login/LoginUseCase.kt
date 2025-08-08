package com.qburst.bind.skillforge.quiz.domain.usecase.login

import com.qburst.bind.skillforge.quiz.domain.model.LoginData
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser

interface LoginUseCase {
    suspend fun login(googleUser: GoogleUser): Result<LoginData>

    suspend fun saveAccessAndRefreshToken(accessToken: String?, refreshToken: String?)

    suspend fun getAccessToken(): String?

    suspend fun isUserLoggedIn(): Boolean

    suspend fun saveUserLogin(isLoggedIn: Boolean)

    suspend fun getRefreshedToken(): String?
}