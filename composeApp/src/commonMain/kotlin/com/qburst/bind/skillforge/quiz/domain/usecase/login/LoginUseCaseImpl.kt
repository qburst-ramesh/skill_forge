package com.qburst.bind.skillforge.quiz.domain.usecase.login

import com.qburst.bind.skillforge.quiz.domain.model.LoginData
import com.qburst.bind.skillforge.quiz.domain.repo.AuthRepo
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser

class LoginUseCaseImpl(private val repo: AuthRepo) : LoginUseCase {
    override suspend fun login(googleUser: GoogleUser): Result<LoginData> {
        return repo.login(googleUser = googleUser)
    }
}