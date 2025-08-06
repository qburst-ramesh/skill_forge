package com.qburst.bind.skillforge.quiz.domain.repo

import com.qburst.bind.skillforge.quiz.domain.model.HomeData

interface HomeRepo {
    suspend fun getHomeData(): Result<HomeData>
}