package com.qburst.bind.skillforge.quiz.presentation.ui.quiz

import com.qburst.bind.skillforge.quiz.domain.model.QuizDifficultyLevel

sealed class QuizUiEvent {
    object OnLoadQuizRules : QuizUiEvent()
    object OnDisplayDifficultyLevels : QuizUiEvent()
    data class OnDifficultyLevelSelected(val level: QuizDifficultyLevel) : QuizUiEvent()
    data class OnStartQuiz(val isAccepted: Boolean) : QuizUiEvent()
}