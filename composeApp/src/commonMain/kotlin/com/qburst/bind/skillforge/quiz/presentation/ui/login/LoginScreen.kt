package com.qburst.bind.skillforge.quiz.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.qburst.bind.skillforge.quiz.presentation.components.DualTitle
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleButtonUiContainer
import com.qburst.bind.skillforge.quiz.presentation.components.GoogleUser
import com.qburst.bind.skillforge.quiz.presentation.theme.FontSize
import com.qburst.bind.skillforge.quiz.presentation.theme.SpacerSize
import io.github.aakira.napier.Napier
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.google_icon
import kotlinproject.composeapp.generated.resources.lato_black
import kotlinproject.composeapp.generated.resources.lato_regular
import kotlinproject.composeapp.generated.resources.sign_in_with_google
import kotlinproject.composeapp.generated.resources.sign_with_google
import kotlinx.coroutines.flow.filter
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Preview
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val viewModel: LoginViewModel = koinInject()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiState) {
        viewModel.uiState.filter { it.isLoggedIn }.collect {
            onLoginSuccess()
        }
    }

    fun onReceiveOAuth(result: Result<GoogleUser?>) {
        result.onFailure {
            Napier.d(message = it.message ?: "Error receiving OAuth token", tag = "LoginScreen")
        }.onSuccess {
            viewModel.onEvent(event = LoginUiEvent.OnOAuthTokenReceived(userData = it))
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(
                horizontal = SpacerSize.size_16,
                vertical = SpacerSize.size_16
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            DualTitle(textSize = FontSize.size_32)

            Spacer(modifier = Modifier.height(SpacerSize.size_16))

            Text(
                stringResource(Res.string.sign_in_with_google), fontFamily = FontFamily(
                    Font(
                        resource = Res.font.lato_regular,
                        weight = FontWeight.Bold,
                        style = FontStyle.Normal
                    )
                ), color = Color.Black, fontSize = FontSize.size_16
            )

            Box(
                modifier = Modifier.fillMaxHeight(fraction = 0.25f)
            )

            GoogleButtonUiContainer(
                modifier = Modifier.fillMaxWidth(fraction = 0.75f)
                    .height(height = SpacerSize.size_48),
                onGoogleSignInResult = ::onReceiveOAuth
            ) {
                Button(
                    onClick = {
                        this.onClick()
                        viewModel.onEvent(
                            LoginUiEvent.OnGoogleLoginClick
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    elevation = ButtonDefaults.buttonElevation(SpacerSize.size_4),
                    shape = RoundedCornerShape(SpacerSize.size_8)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.google_icon),
                            contentDescription = null,
                            modifier = Modifier.size(SpacerSize.size_24)
                        )
                        Spacer(modifier = Modifier.width(SpacerSize.size_16))
                        Text(
                            text = stringResource(Res.string.sign_with_google),
                            color = Color.Black,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily(
                                Font(
                                    resource = Res.font.lato_black,
                                    weight = FontWeight.Normal,
                                    style = FontStyle.Normal
                                )
                            )
                        )
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxHeight(fraction = 0.1f)
            )

            if (state.loading) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(SpacerSize.size_16))
            }

            if (state.error.isNotEmpty()) {
                Text(state.error, color = Color.Red)
                Spacer(modifier = Modifier.height(SpacerSize.size_8))
            }
        }
    }
}
