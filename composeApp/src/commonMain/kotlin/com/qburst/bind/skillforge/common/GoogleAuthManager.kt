package com.qburst.bind.skillforge.common

import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser

// commonMain
expect class GoogleAuthManager {
    suspend fun signIn(): Result<GoogleUser?>
}
