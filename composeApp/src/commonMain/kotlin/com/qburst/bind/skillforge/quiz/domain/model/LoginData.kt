package com.qburst.bind.skillforge.quiz.domain.model

data class LoginData(
    val access: String = "",
    val refresh: String = "",
    val department: String = "",
    val designation: String = "",
    val email: String = "",
    val employeeId: String = "",
    val firstTimeLogin: Boolean = false,
    val id: Int = 0,
    val location: String = "",
    val name: String = "",
    val otherSkills: List<Any?> = listOf(),
    val stream: Int = 0,
    val streamLevel: String = "",
    val userRole: String = "",
    val yearsOfExperience: Int = 0
)
