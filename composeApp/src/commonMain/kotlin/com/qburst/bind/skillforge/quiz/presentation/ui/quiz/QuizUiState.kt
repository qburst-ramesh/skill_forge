package com.qburst.bind.skillforge.quiz.presentation.ui.quiz

import com.qburst.bind.skillforge.quiz.domain.model.QuizDifficultyLevel
import com.qburst.bind.skillforge.quiz.domain.model.QuizRulesData

data class QuizUiState(
    val isUserAcceptedRules: Boolean = false,
    val quizRulesData: QuizRulesData = QuizRulesData(),
    val difficultyLevel: QuizDifficultyLevel = QuizDifficultyLevel.DEFAULT,
    val isSelectDifficultLevel: Boolean = false
)