package com.qburst.bind.skillforge.quiz.data.repo.home.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponseData(
    @SerialName("badge_info")
    val badgeInfo: BadgeInfo? = BadgeInfo(),
    @SerialName("badge_level")
    val badgeLevel: String? = "",
    @SerialName("category")
    val category: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("is_badge_topic")
    val isBadgeTopic: Boolean = false,
    @SerialName("is_generated")
    val isGenerated: Boolean = false,
    @SerialName("is_homepage")
    val isHomepage: Boolean = false,
    @SerialName("name")
    val name: String = "",
    @SerialName("skill")
    val skill: String = "",
    @SerialName("skill_badge_summary")
    val skillBadgeSummary: SkillBadgeSummary = SkillBadgeSummary()
)

@Serializable
data class SkillBadgeSummary(
    @SerialName("current_skill_badge")
    val currentSkillBadge: BadgeInfo? = BadgeInfo(),
    @SerialName("is_badge_skill")
    val isBadgeSkill: Boolean = false
)

@Serializable
data class BadgeInfo(
    @SerialName("badge_level")
    val badgeLevel: String = "",
    @SerialName("has_badge_progression")
    val hasBadgeProgression: Boolean = false,
    @SerialName("skill_name")
    val skillName: String = ""
)