package com.qburst.bind.skillforge.quiz.presentation.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qburst.bind.skillforge.quiz.domain.usecase.quiz.QuizUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizUseCase: QuizUseCase
) : ViewModel() {

    private val _uiEvent = MutableStateFlow(QuizUiState())
    val uiEvent = _uiEvent.asStateFlow()

    init {
        onEvent(QuizUiEvent.OnLoadQuizRules)
    }

    fun onEvent(event: QuizUiEvent) {
        when (event) {
            is QuizUiEvent.OnLoadQuizRules -> {
                // Logic to load quiz rules
                viewModelScope.launch {
                    quizUseCase.loadQuizRulesData().collect { rulesData ->
                        _uiEvent.value = _uiEvent.value.copy(
                            isUserAcceptedRules = false,
                            quizRulesData = rulesData
                        )
                    }
                }
            }

            is QuizUiEvent.OnDisplayDifficultyLevels -> {

            }

            is QuizUiEvent.OnDifficultyLevelSelected -> {
                _uiEvent.value = _uiEvent.value.copy(
                    difficultyLevel = event.level
                )
            }

            is QuizUiEvent.OnStartQuiz -> {
                _uiEvent.value = _uiEvent.value.copy(
                    isUserAcceptedRules = event.isAccepted
                )
            }
        }
    }
}