package com.qburst.bind.skillforge.quiz.domain.usecase.home

import com.qburst.bind.skillforge.quiz.domain.model.HomeData
import com.qburst.bind.skillforge.quiz.domain.repo.HomeRepo

class HomeUseCaseImpl(
    val homeRepo: HomeRepo
) : HomeUseCase {
    override suspend fun getHomeData(): Result<HomeData> {
        return homeRepo.getHomeData()
    }
}