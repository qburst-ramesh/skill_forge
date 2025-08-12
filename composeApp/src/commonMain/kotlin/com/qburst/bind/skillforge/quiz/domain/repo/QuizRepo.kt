package com.qburst.bind.skillforge.quiz.domain.repo

import com.qburst.bind.skillforge.quiz.domain.model.QuizRulesData
import kotlinx.coroutines.flow.Flow

interface QuizRepo {
    suspend fun loadQuizRulesData(): Flow<QuizRulesData>
}