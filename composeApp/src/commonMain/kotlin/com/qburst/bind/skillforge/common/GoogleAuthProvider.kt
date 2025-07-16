package com.qburst.bind.skillforge.common

import androidx.compose.runtime.Composable

expect class GoogleAuthProvider {

    @Composable
    fun getUiProvider(): GoogleAuthManager

    suspend fun signOut()
}