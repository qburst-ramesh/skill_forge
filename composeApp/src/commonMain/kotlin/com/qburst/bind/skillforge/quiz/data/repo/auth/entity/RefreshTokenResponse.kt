package com.qburst.bind.skillforge.quiz.data.repo.auth.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("access")
    val access: String = "",
    @SerialName("refresh")
    val refresh: String = ""
)

@Serializable
data class RefreshTokenResponse(
    @SerialName("data")
    val data: Data = Data(),
    @SerialName("message")
    val message: String = "",
    @SerialName("status")
    val status: String = ""
)