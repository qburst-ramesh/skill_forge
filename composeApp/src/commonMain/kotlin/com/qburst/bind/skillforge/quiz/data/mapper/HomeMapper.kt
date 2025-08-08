package com.qburst.bind.skillforge.quiz.data.mapper

import com.qburst.bind.skillforge.quiz.data.repo.home.entity.HomeResponseData
import com.qburst.bind.skillforge.quiz.domain.model.BadgeData
import com.qburst.bind.skillforge.quiz.domain.model.BadgeLevel
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

    private fun transformTopic(homeResponseData: List<HomeResponseData?>): List<TopicData?> {
        return homeResponseData.map {
            it?.toTopicData()
        }
    }
}

fun HomeResponseData.toTopicData(): TopicData = TopicData(
    id = id,
    name = name,
    description = description,
    skill = skill,
    title = name,
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
