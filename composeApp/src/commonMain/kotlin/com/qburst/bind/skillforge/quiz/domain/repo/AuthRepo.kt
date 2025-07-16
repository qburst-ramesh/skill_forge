package com.qburst.bind.skillforge.quiz.domain.repo

import com.qburst.bind.skillforge.quiz.domain.model.LoginData
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser

interface AuthRepo {
    suspend fun login(googleUser: GoogleUser): Result<LoginData>
}