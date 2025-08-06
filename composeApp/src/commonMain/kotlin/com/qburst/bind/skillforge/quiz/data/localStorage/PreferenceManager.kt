package com.qburst.bind.skillforge.quiz.data.localStorage

interface PreferenceManager {

    suspend fun saveAccessToken(token: String?)

    suspend fun getAccessToken(): String?

    suspend fun saveRefreshToken(token: String?)

    suspend fun getRefreshToken(): String?

    suspend fun setUserLoggedIn(isLoggedIn: Boolean = false)

    suspend fun isUserLoggedIn(): Boolean
}