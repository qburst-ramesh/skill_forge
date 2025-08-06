package com.qburst.bind.skillforge.quiz.data.mapper

import com.qburst.bind.skillforge.quiz.data.repo.home.entity.HomeResponseData
import com.qburst.bind.skillforge.quiz.domain.model.HomeData
import com.qburst.bind.skillforge.quiz.domain.model.TopicData

class HomeMapper() : Mapper<HomeData, List<HomeResponseData?>> {
    override fun transform(entity: List<HomeResponseData?>): HomeData {
        return transformHomeData(homeResponseData = entity)
    }

    override fun transformToList(entity: List<HomeResponseData?>): List<HomeData> =
        listOf(transform(entity = entity))

    private fun transformHomeData(homeResponseData: List<HomeResponseData?>): HomeData {
        return HomeData(
            topicList = transformTopic(homeResponseData = homeResponseData)
        )
    }

    private fun transformTopic(homeResponseData: List<HomeResponseData?>): List<TopicData> {
        return homeResponseData.map {
            TopicData(
                id = it?.id ?: 0,
                name = it?.name ?: "",
                description = it?.description ?: "",
                skill = it?.skill,
                title = it?.name ?: "",
                imageUrl = "",
                isCompleted = false
            )
        }
    }
}