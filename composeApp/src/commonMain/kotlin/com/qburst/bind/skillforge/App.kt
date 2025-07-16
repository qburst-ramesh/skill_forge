package com.qburst.bind.skillforge

import androidx.compose.runtime.Composable
import com.qburst.bind.skillforge.quiz.di.appModule
import com.qburst.bind.skillforge.quiz.presentation.theme.AppTheme
import com.qburst.bind.skillforge.quiz.utils.Router
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinAppDeclaration

@Composable
@Preview
fun App(koinAppDeclaration: KoinAppDeclaration? = null) {

    KoinApplication(application = {
        koinAppDeclaration?.invoke(this)
        modules(appModule())
    }) {
        AppTheme {
            Router()
        }
    }
}