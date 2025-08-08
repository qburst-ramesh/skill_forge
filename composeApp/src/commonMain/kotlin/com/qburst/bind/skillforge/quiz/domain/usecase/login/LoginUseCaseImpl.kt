package com.qburst.bind.skillforge.quiz.domain.usecase.login

import com.qburst.bind.skillforge.quiz.data.localStorage.PreferenceManager
import com.qburst.bind.skillforge.quiz.domain.model.LoginData
import com.qburst.bind.skillforge.quiz.domain.repo.AuthRepo
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser

class LoginUseCaseImpl(
    private val repo: AuthRepo,
    private val preferenceManager: PreferenceManager
) : LoginUseCase {
    override suspend fun login(googleUser: GoogleUser): Result<LoginData> {
        return repo.login(googleUser = googleUser)
    }

    override suspend fun saveAccessAndRefreshToken(accessToken: String?, refreshToken: String?) {
        preferenceManager.saveAccessToken(token = accessToken)
        preferenceManager.saveRefreshToken(token = refreshToken)
    }

    override suspend fun getAccessToken(): String? {
        return preferenceManager.getAccessToken()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return preferenceManager.isUserLoggedIn()
    }

    override suspend fun saveUserLogin(isLoggedIn: Boolean) {
        preferenceManager.setUserLoggedIn(isLoggedIn = isLoggedIn)
    }

    override suspend fun getRefreshedToken(): String? = preferenceManager.getRefreshToken()
}