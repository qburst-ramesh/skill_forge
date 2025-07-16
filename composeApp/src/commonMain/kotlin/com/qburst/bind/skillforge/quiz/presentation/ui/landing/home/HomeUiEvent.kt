package com.qburst.bind.skillforge.quiz.presentation.ui.landing.home

sealed class HomeUiEvent {
    object onFetchHomeData : HomeUiEvent()
    object onExploreClick : HomeUiEvent()
}