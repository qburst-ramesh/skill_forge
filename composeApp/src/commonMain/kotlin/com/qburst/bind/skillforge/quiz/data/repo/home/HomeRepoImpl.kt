package com.qburst.bind.skillforge.quiz.data.repo.home

import com.qburst.bind.skillforge.quiz.data.model.ApiResponse
import com.qburst.bind.skillforge.quiz.data.model.ErrorType
import com.qburst.bind.skillforge.quiz.data.model.getErrorData
import com.qburst.bind.skillforge.quiz.data.networking.ApiClientHelper
import com.qburst.bind.skillforge.quiz.data.networking.EndPoints
import com.qburst.bind.skillforge.quiz.data.networking.apiEndPoint
import com.qburst.bind.skillforge.quiz.data.networking.safeRequest
import com.qburst.bind.skillforge.quiz.data.repo.BaseResponse
import com.qburst.bind.skillforge.quiz.data.repo.home.entity.HomeResponseData
import com.qburst.bind.skillforge.quiz.domain.model.BadgeData
import com.qburst.bind.skillforge.quiz.domain.model.BadgeLevel
import com.qburst.bind.skillforge.quiz.domain.model.HomeData
import com.qburst.bind.skillforge.quiz.domain.model.TopicData
import com.qburst.bind.skillforge.quiz.domain.repo.HomeRepo
import com.qburst.bind.skillforge.quiz.domain.repo.TokenProvider
import com.qburst.bind.skillforge.quiz.utils.toThrowable
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import kotlinx.serialization.Serializable

typealias HomeBaseResponse = BaseResponse<HomeResponseData?>

@Serializable
data class HomeRequest(
    val is_homepage: Boolean = true
)

class HomeRepoImpl(
    val apiClientHelper: ApiClientHelper,
    val tokenProvider: TokenProvider
) : HomeRepo {
    override suspend fun getHomeData(): Result<HomeData> {
        val response = apiClientHelper.client.safeRequest<HomeBaseResponse, HomeRequest>(
            block = {
                apiEndPoint(endPoint = EndPoints.Topic_list)
                method = HttpMethod.Get
                parameter(
                    "is_homepage", "true"
                )

            },
            tokenProvider = tokenProvider
        )
        return when (response) {
            is ApiResponse.Error -> {
                Result.failure("Error: ${response.getErrorData(ErrorType.LOGIN)}".toThrowable())
            }

            is ApiResponse.Success<HomeBaseResponse> -> {
                val data: List<HomeResponseData?> = response.body.data
//                val mapperData: HomeData = mapper.transform(data)
                val dat: List<TopicData?> = data.map {
                    it?.toTopicData()
                }

                Result.success(
                    HomeData(
                        topicList = dat
                    )
                )
            }
        }
    }
}

fun HomeResponseData.toTopicData(): TopicData = TopicData(
    id = id,
    name = name,
    description = description ?: "",
    skill = skill,
    title = name ?: "",
    imageUrl = "",
    isCompleted = false,
    badgeData = this.toBadgeData()
)

fun HomeResponseData.toBadgeData(): BadgeData? {
    return if (this.badgeInfo != null) {
        BadgeData(
            badgeLevel = BadgeLevel.fromLevel(level = this.badgeInfo.badgeLevel),
            skillName = this.badgeInfo.skillName,
            hasBadgeProgress = this.badgeInfo.hasBadgeProgression
        )
    } else {
        null
    }
}
