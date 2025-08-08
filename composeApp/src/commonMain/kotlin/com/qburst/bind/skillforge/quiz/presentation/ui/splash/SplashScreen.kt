package com.qburst.bind.skillforge.quiz.presentation.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.qburst.bind.skillforge.quiz.presentation.theme.FontSize
import com.qburst.bind.skillforge.quiz.presentation.theme.SpacerSize
import io.github.aakira.napier.Napier
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.app_name
import kotlinproject.composeapp.generated.resources.lato_bold
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
internal fun SplashScreen(onNavigate: (token: String?) -> Unit = {}) {
    val viewModel: SplashViewModel = koinInject()
    val state by viewModel.uiState.collectAsState()
    var isShowSplash by remember { mutableStateOf(true) }

    LaunchedEffect(viewModel.uiState) {
        delay(1000)
        isShowSplash = false
        delay(3000)
        viewModel.uiState.filter { !it.loading }.collect {

            Napier.e(
                message = "Splash Token ${state.token}",
                tag = "SplashScreen"
            )
            onNavigate(state.token)
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = isShowSplash,
                enter = fadeIn(animationSpec = tween(500)),
                exit = fadeOut(animationSpec = tween(500))
            ) {
                Text(
                    stringResource(Res.string.app_name), fontFamily = FontFamily(
                        Font(
                            resource = Res.font.lato_bold,
                            weight = FontWeight.Normal,
                            style = FontStyle.Normal
                        )
                    ),
                    color = MaterialTheme.colorScheme.background,
                    fontSize = FontSize.size_58
                )
            }
            CircularProgressIndicator(
                modifier = Modifier.padding(top = SpacerSize.size_16),
                color = MaterialTheme.colorScheme.tertiary
            )
        }

    }
}
