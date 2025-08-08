package com.qburst.bind.skillforge.quiz.presentation.ui.landing.home

import com.qburst.bind.skillforge.quiz.domain.model.HomeData

sealed class HomeUiEvent {

    data class OnHomeDataReceived(val homeData: HomeData) :
        HomeUiEvent() // Replace Int with actual data type

    data class OnError(val error: String) : HomeUiEvent()
    data class OnLoadHomeData(val forceRefresh: Boolean = false) : HomeUiEvent()
}