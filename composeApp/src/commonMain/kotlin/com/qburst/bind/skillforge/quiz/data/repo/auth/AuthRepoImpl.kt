@file:OptIn(ExperimentalTime::class)

package com.qburst.bind.skillforge.quiz.data.repo.auth

import com.qburst.bind.skillforge.quiz.data.mapper.Mapper
import com.qburst.bind.skillforge.quiz.data.model.ApiResponse
import com.qburst.bind.skillforge.quiz.data.model.ErrorType
import com.qburst.bind.skillforge.quiz.data.model.getErrorData
import com.qburst.bind.skillforge.quiz.data.networking.ApiClientHelper
import com.qburst.bind.skillforge.quiz.data.networking.EndPoints
import com.qburst.bind.skillforge.quiz.data.networking.apiEndPoint
import com.qburst.bind.skillforge.quiz.data.networking.safeRequest
import com.qburst.bind.skillforge.quiz.data.repo.auth.entity.UserLoginResponse
import com.qburst.bind.skillforge.quiz.domain.model.LoginData
import com.qburst.bind.skillforge.quiz.domain.repo.AuthRepo
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser
import com.qburst.bind.skillforge.quiz.utils.toThrowable
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

@Serializable
data class LoginRequest(
    val auth_token: String
)

class AuthRepoImpl(
    val apiClientHelper: ApiClientHelper,
    val mapper: Mapper<LoginData, UserLoginResponse>
) : AuthRepo {
    override suspend fun login(googleUser: GoogleUser): Result<LoginData> {
        val response = apiClientHelper.client.safeRequest<UserLoginResponse, GoogleUser> {
            apiEndPoint(EndPoints.login)
            method = HttpMethod.Post
            setBody(
                LoginRequest(
                    auth_token = googleUser.token
                )
            )
        }
        return when (response) {
            is ApiResponse.Error -> {
                Result.failure("Error: ${response.getErrorData(ErrorType.LOGIN)}".toThrowable())
            }

            is ApiResponse.Success<UserLoginResponse> -> {
                val data = response.body
                val mapperData = mapper.transform(data)
                Result.success(mapperData)
            }
        }
    }
}