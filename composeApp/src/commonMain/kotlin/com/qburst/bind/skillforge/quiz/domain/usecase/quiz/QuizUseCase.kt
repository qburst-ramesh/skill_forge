package com.qburst.bind.skillforge.quiz.domain.usecase.quiz

import com.qburst.bind.skillforge.quiz.domain.model.QuizRulesData
import kotlinx.coroutines.flow.Flow

interface QuizUseCase {
    suspend fun loadQuizRulesData(): Flow<QuizRulesData>
}