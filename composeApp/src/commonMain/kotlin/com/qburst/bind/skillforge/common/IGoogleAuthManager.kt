package com.qburst.bind.skillforge.common

interface IGoogleAuthManager {
    suspend fun signIn(): String?

    suspend fun signOut()
}