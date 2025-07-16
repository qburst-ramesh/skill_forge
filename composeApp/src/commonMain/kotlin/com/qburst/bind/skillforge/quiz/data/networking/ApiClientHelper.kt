package com.qburst.bind.skillforge.quiz.data.networking

import com.qburst.bind.skillforge.common.httpClient
import com.qburst.bind.skillforge.quiz.data.model.ApiResponse
import com.qburst.bind.skillforge.quiz.data.repo.auth.entity.UserData
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthConfig
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class ApiClientHelper() {
    var client: HttpClient

    init {
        client = createClient(userData = null)
    }

    fun authClient(userInfo: UserData) {
        client = createClient(userData = userInfo)
    }

    fun createClient(userData: UserData?): HttpClient {
        return httpClient {

            install(HttpTimeout) {
                socketTimeoutMillis = 60_000
                requestTimeoutMillis = 60_000
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "ApiCall", message = message)
                    }
                }
                level = LogLevel.ALL
            }

            install(Auth) {
                userData?.let {
                    bearer {
                        setupAuth(authToken = "Token")
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }

        }
    }

    private fun HttpRequestBuilder.isLoginUrl(): Boolean {
        return this.url.toString().contains(EndPoints.login)
    }

    fun BearerAuthConfig.setupAuth(
        authToken: String?
    ) {
        sendWithoutRequest { hb ->
            Napier.d(tag = "ApiHelper", message = "sendWithoutRequest")
            if (hb.isLoginUrl()) {
                return@sendWithoutRequest false
            }
            authToken?.let {
                hb.bearerAuth(it)
                Napier.d(
                    tag = "ApiHelper",
                    message = "Authorization: ${hb.headers[HttpHeaders.Authorization]}"
                )
            }
            true
        }
        loadTokens {
            Napier.d(tag = "ApiHelper", message = "loadTokens userInfo=$authToken")
            authToken?.let {
                BearerTokens(it, "")
            }
        }
//        refreshTokens {
//        }
    }
}


fun HttpRequestBuilder.apiEndPoint(endPoint: String) {
    headers {
        set("Content-Type", "application/json")
    }
    url(APIConstants.apiHost + endPoint)
}


suspend inline fun <reified T, reified E> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): ApiResponse<T, E> {
    return try {
        val response = request { block() }
        ApiResponse.Success(response.body())
    } catch (e: ClientRequestException) {
        e.printStackTrace()
        if (e.response.status == HttpStatusCode.Unauthorized)
            ApiResponse.Error.Unauthorized(e.response.status.value, e.errorBody())
        else ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: ServerResponseException) {
        e.printStackTrace()
        ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
    } catch (e: IOException) {
        e.printStackTrace()
        ApiResponse.Error.NetworkError
    } catch (e: SerializationException) {
        e.printStackTrace()
        ApiResponse.Error.SerializationError
    } catch (e: Exception) {
        e.printStackTrace()
        Napier.e(tag = "ApiCall", message = e.message ?: "")
        ApiResponse.Error.SerializationError
    }
}

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.body()
    } catch (e: Exception) {
        null
    }


