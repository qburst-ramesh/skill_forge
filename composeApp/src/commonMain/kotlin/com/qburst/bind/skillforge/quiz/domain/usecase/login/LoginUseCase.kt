package com.qburst.bind.skillforge.quiz.domain.usecase.login

import com.qburst.bind.skillforge.quiz.domain.model.LoginData
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser

interface LoginUseCase {
    suspend fun login(googleUser: GoogleUser): Result<LoginData>
}