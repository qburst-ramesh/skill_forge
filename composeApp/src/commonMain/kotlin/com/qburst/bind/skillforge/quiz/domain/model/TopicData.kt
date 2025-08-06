package com.qburst.bind.skillforge.quiz.domain.model

import androidx.compose.ui.graphics.Color

data class TopicData(
    val id: Int,
    val title: String,
    val name: String,
    val description: String,
    val imageUrl: String? = null,
    val isCompleted: Boolean = false,
    val skill: String? = null,
    val badgeData: BadgeData? = null,
)

data class BadgeData(
    val badgeLevel: BadgeLevel,
    val skillName: String,
    val hasBadgeProgress: Boolean = false,
)

enum class BadgeLevel(val level: String, val backgroundColor: Color) {
    BEGINNER(level = "Beginner", Color.Yellow),
    INTERMEDIATE(level = "Intermediate", Color.Blue),
    EXPERT(level = "Expert", Color.Green);

    companion object {
        fun fromLevel(level: String): BadgeLevel {
            return entries.find { it.level.equals(level, ignoreCase = true) }
                ?: BEGINNER
        }
    }
}
