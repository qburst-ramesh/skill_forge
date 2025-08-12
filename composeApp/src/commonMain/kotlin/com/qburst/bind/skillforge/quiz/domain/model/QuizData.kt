package com.qburst.bind.skillforge.quiz.domain.model

import androidx.compose.ui.graphics.Color

enum class QuizDifficultyLevel(val level: String, val backgroundColor: Color) {
    EASY(level = "Easy", backgroundColor = Color.Yellow),
    MEDIUM(level = "Medium", backgroundColor = Color.Yellow),
    HARD(level = "Hard", backgroundColor = Color.Yellow),
    MIXED(level = "Mixed", backgroundColor = Color.Yellow),
    DEFAULT(level = "Not Selected", backgroundColor = Color.Transparent);

    companion object {
        fun fromLevel(level: String): QuizDifficultyLevel {
            return entries.find { it.level.equals(level, ignoreCase = true) } ?: DEFAULT
        }

        fun getAllLevels(): List<QuizDifficultyLevel> {
            return listOf(
                EASY,
                MEDIUM,
                HARD,
                MIXED
            )
        }
    }
}

val rulesData = listOf(
    "A timer will run throughout the quiz and cannot be paused. Complete the quiz before the timer expires.",
    "Completing the quiz faster provides a competitive advantage.",
    "Questions can be answered in any order.",
    "Click the Submit button to submit the quiz.",
    "Selecting an option again will deselect it.",
    "Use the back and next buttons to switch between questions.",
    "You can also navigate to any question by clicking the corresponding one in the question list panel on the right side.",
    "The question list panel can be collapsed and expanded back by clicking the icon in the question box.",
    "Click the quit link in the bottom to leave the quiz."
)

data class QuizRulesData(
    val rulesList: List<String> = emptyList()
)
