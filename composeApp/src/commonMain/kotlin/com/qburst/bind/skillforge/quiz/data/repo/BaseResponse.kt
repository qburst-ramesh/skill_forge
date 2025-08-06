package com.qburst.bind.skillforge.quiz.data.repo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("data")
    val data: List<T> = listOf(),
    @SerialName("message")
    val message: String = "",
    @SerialName("status")
    val status: String = ""
)