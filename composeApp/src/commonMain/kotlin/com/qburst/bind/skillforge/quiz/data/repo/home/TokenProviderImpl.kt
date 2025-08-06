package com.qburst.bind.skillforge.quiz.data.repo.home

import com.qburst.bind.skillforge.quiz.data.localStorage.PreferenceManager
import com.qburst.bind.skillforge.quiz.data.networking.EndPoints
import com.qburst.bind.skillforge.quiz.data.networking.apiEndPoint
import com.qburst.bind.skillforge.quiz.data.repo.auth.entity.RefreshTokenResponse
import com.qburst.bind.skillforge.quiz.domain.repo.TokenProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequest(
    val refresh: String
)

class TokenProviderImpl(
    private val preferenceManager: PreferenceManager,
    private val refreshClient: HttpClient
) : TokenProvider {
    override suspend fun getAccessToken(): String? {
        return preferenceManager.getAccessToken()
    }

    override suspend fun getRefreshToken(): String? {
        return preferenceManager.getRefreshToken()
    }

    override suspend fun refreshToken(): Boolean {
        val refreshToken = getRefreshToken() ?: return false
        return try {
            val response: HttpResponse = refreshClient.post {
                headers {
                    set("Content-Type", "application/json")
                }
                apiEndPoint(EndPoints.Refresh_Token)  // Replace with your actual endpoint
                method = HttpMethod.Post
                setBody(RefreshTokenRequest(refresh = refreshToken))
            }
            val apiData = response.body<RefreshTokenResponse>()
            preferenceManager.saveRefreshToken(token = apiData.data.refresh)
            preferenceManager.saveAccessToken(token = apiData.data.access)
            true
        } catch (e: Exception) {
            false
        }
        return false
    }
}