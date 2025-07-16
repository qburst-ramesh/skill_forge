package com.qburst.bind.skillforge.quiz.presentation.ui.landing.home

import androidx.lifecycle.ViewModel

class HomeViewModel() : ViewModel() {

    init {

    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.onExploreClick -> {
                TODO()
            }

            HomeUiEvent.onFetchHomeData -> {
                TODO()
            }
        }
    }
}