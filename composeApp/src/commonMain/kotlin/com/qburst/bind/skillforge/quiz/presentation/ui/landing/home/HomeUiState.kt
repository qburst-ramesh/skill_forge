package com.qburst.bind.skillforge.quiz.presentation.ui.landing.home

import com.qburst.bind.skillforge.quiz.domain.model.HomeData

data class HomeUiState(
    val loading: Boolean = true,
    val error: String? = null,
    val homeData: HomeData? = null
)