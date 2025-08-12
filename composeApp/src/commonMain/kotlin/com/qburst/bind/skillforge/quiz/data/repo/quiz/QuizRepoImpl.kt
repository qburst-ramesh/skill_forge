package com.qburst.bind.skillforge.quiz.data.repo.quiz

import com.qburst.bind.skillforge.quiz.domain.model.QuizRulesData
import com.qburst.bind.skillforge.quiz.domain.model.rulesData
import com.qburst.bind.skillforge.quiz.domain.repo.QuizRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.compose.resources.InternalResourceApi

class QuizRepoImpl(

) : QuizRepo {

    private val fileName = "quizRules.json"

    @OptIn(InternalResourceApi::class)
    override suspend fun loadQuizRulesData(): Flow<QuizRulesData> {

        return flow {
            emit(
                QuizRulesData(
                    rulesList = rulesData
                )
            )
        }
    }

    // Additional methods can be added here if needed in the future.) {
}