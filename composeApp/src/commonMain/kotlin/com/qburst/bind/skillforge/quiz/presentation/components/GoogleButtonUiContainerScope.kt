package com.qburst.bind.skillforge.quiz.presentation.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.qburst.bind.skillforge.common.GoogleAuthProvider
import com.qburst.bind.skillforge.quiz.presentation.theme.SpacerSize
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data class GoogleUser(
    val token: String,
    val name: String?,
    val profilePictureUrl: String?,
    val phoneNumber: String? = null
)

interface GoogleButtonUiContainerScope {
    fun onClick()
}

@Composable
fun GoogleButtonUiContainer(
    modifier: Modifier = Modifier,
    onGoogleSignInResult: (Result<GoogleUser?>) -> Unit,
    content: @Composable GoogleButtonUiContainerScope.() -> Unit,
) {
    val googleAuthProvider = koinInject<GoogleAuthProvider>()
    val googleAuthUiProvider = googleAuthProvider.getUiProvider()
    val coroutineScope = rememberCoroutineScope()
    val uiContainerScope = remember {
        object : GoogleButtonUiContainerScope {
            override fun onClick() {
                coroutineScope.launch {
                    val googleUser = googleAuthUiProvider.signIn()
                    onGoogleSignInResult(googleUser)
                }
            }
        }
    }
    Surface(
        modifier = modifier,
        color = Color.Transparent,
        content = { uiContainerScope.content() },
        shadowElevation = SpacerSize.size_4
    )
}