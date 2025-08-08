package com.qburst.bind.skillforge.quiz.domain.repo

interface TokenProvider {
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun refreshToken(): Boolean
}