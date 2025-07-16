package com.qburst.bind.skillforge.quiz.data.mapper

import com.qburst.bind.skillforge.quiz.data.repo.auth.entity.UserLoginResponse
import com.qburst.bind.skillforge.quiz.domain.model.LoginData

class AuthLoginMapper() : Mapper<LoginData, UserLoginResponse> {

    override fun transform(entity: UserLoginResponse): LoginData {
        return transformLoginData(userLoginResponse = entity)
    }

    override fun transformToList(entity: UserLoginResponse): List<LoginData> =
        listOf(transform(entity = entity))

    private fun transformLoginData(userLoginResponse: UserLoginResponse): LoginData {
        return LoginData(
            access = userLoginResponse.userData.access,
            refresh = userLoginResponse.userData.refresh,
            department = userLoginResponse.userData.user.department,
            designation = userLoginResponse.userData.user.designation,
            email = userLoginResponse.userData.user.email,
            employeeId = userLoginResponse.userData.user.employeeId,
            firstTimeLogin = userLoginResponse.userData.user.firstTimeLogin,
            id = userLoginResponse.userData.user.id,
            location = userLoginResponse.userData.user.location,
            name = userLoginResponse.userData.user.name,
            otherSkills = userLoginResponse.userData.user.otherSkills,
            stream = userLoginResponse.userData.user.stream,
            streamLevel = userLoginResponse.userData.user.streamLevel,
            userRole = userLoginResponse.userData.user.userRole,
            yearsOfExperience = userLoginResponse.userData.user.yearsOfExperience,
        )
    }
}