package com.qburst.bind.skillforge.quiz.domain.usecase.quiz

import com.qburst.bind.skillforge.quiz.domain.model.QuizRulesData
import com.qburst.bind.skillforge.quiz.domain.repo.QuizRepo
import kotlinx.coroutines.flow.Flow

class QuizUseCaseImpl(
    private val quizRepo: QuizRepo
) : QuizUseCase {
    override suspend fun loadQuizRulesData(): Flow<QuizRulesData> {
        return quizRepo.loadQuizRulesData()
    }
}