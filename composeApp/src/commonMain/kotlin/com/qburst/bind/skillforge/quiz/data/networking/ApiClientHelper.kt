package com.qburst.bind.skillforge.quiz.data.networking

import com.qburst.bind.skillforge.common.httpClient
import com.qburst.bind.skillforge.quiz.data.model.ApiResponse
import com.qburst.bind.skillforge.quiz.domain.repo.TokenProvider
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class ApiClientHelper(
    val tokenProvider: TokenProvider
) {
    var client: HttpClient

    init {
        client = createClient()
    }

    fun createClient(): HttpClient {
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
                bearer {
                    sendWithoutRequest { hb ->
                        Napier.d(tag = "ApiHelper", message = "sendWithoutRequest")
                        if (hb.isLoginUrl()) {
                            return@sendWithoutRequest false
                        }
                        true
                    }
                    loadTokens {
                        BearerTokens(
                            accessToken = tokenProvider.getAccessToken() ?: "",
                            refreshToken = tokenProvider.getRefreshToken() ?: ""
                        )
                    }
                    refreshTokens {
                        Napier.d(tag = "ApiHelper", message = "refreshTokens")
                        val refreshed = tokenProvider.refreshToken()
                        if (refreshed) {
                            BearerTokens(
                                accessToken = tokenProvider.getAccessToken() ?: "",
                                refreshToken = tokenProvider.getRefreshToken() ?: ""
                            )
                        } else {
                            null
                        }
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
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(HttpHeaders.Accept, ContentType.Application.Json)
            }
        }
    }

    private fun HttpRequestBuilder.isLoginUrl(): Boolean {
        return this.url.toString().contains(EndPoints.Login)
    }
}


fun HttpRequestBuilder.apiEndPoint(endPoint: String) {
    headers {
        set("Content-Type", "application/json")
    }
    url(APIConstants.ApiHost + endPoint)
}

suspend inline fun <reified T, reified E> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
    tokenProvider: TokenProvider
): ApiResponse<T, E> {
    return try {
        val response = request { block() }
        ApiResponse.Success(response.body())
    } catch (e: ClientRequestException) {
        e.printStackTrace()
        if (e.response.status == HttpStatusCode.Unauthorized) {
            val refresh = tokenProvider.refreshToken()
            if (refresh) {
                this.request { block() }
            }
            ApiResponse.Error.Unauthorized(e.response.status.value, e.errorBody())
        } else {
            ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
        }
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


