package com.qburst.bind.skillforge.quiz.data.repo.auth.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLoginResponse(
    @SerialName("data")
    val userData: UserData = UserData(),
    @SerialName("message")
    val message: String = "",
    @SerialName("status")
    val status: String = ""
)

@Serializable
data class User(
    @SerialName("department")
    val department: String = "",
    @SerialName("designation")
    val designation: String = "",
    @SerialName("email")
    val email: String = "",
    @SerialName("employee_id")
    val employeeId: String = "",
    @SerialName("first_time_login")
    val firstTimeLogin: Boolean = false,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("location")
    val location: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("other_skills")
    val otherSkills: List<String?> = listOf(),
    @SerialName("stream")
    val stream: Int = 0,
    @SerialName("stream_level")
    val streamLevel: String = "",
    @SerialName("user_role")
    val userRole: String = "",
    @SerialName("years_of_experience")
    val yearsOfExperience: Int = 0
)

@Serializable
data class UserData(
    @SerialName("access")
    val access: String = "",
    @SerialName("refresh")
    val refresh: String = "",
    @SerialName("user")
    val user: User = User()
)
