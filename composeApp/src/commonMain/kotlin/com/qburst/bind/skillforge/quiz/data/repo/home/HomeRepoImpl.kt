package com.qburst.bind.skillforge.quiz.data.repo.home

import com.qburst.bind.skillforge.quiz.data.mapper.Mapper
import com.qburst.bind.skillforge.quiz.data.model.ApiResponse
import com.qburst.bind.skillforge.quiz.data.model.ErrorType
import com.qburst.bind.skillforge.quiz.data.model.getErrorData
import com.qburst.bind.skillforge.quiz.data.networking.ApiClientHelper
import com.qburst.bind.skillforge.quiz.data.networking.EndPoints
import com.qburst.bind.skillforge.quiz.data.networking.apiEndPoint
import com.qburst.bind.skillforge.quiz.data.networking.safeRequest
import com.qburst.bind.skillforge.quiz.data.repo.BaseResponse
import com.qburst.bind.skillforge.quiz.data.repo.home.entity.HomeResponseData
import com.qburst.bind.skillforge.quiz.domain.model.HomeData
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
    val tokenProvider: TokenProvider,
    val mapper: Mapper<HomeData, List<HomeResponseData?>>,
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
                val mapperData: HomeData = mapper.transform(data)
                if (mapperData.topicList.isEmpty()) {
                    return Result.failure("No topics found".toThrowable())
                }
                Result.success(
                    HomeData(
                        topicList = mapperData.topicList
                    )
                )
            }
        }
    }
}
