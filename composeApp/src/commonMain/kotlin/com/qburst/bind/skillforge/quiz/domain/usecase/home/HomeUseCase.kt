package com.qburst.bind.skillforge.quiz.domain.usecase.home

import com.qburst.bind.skillforge.quiz.domain.model.HomeData

interface HomeUseCase {
    suspend fun getHomeData(): Result<HomeData>
}