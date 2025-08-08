package com.qburst.bind.skillforge.quiz.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.qburst.bind.skillforge.quiz.presentation.theme.SpacerSize
import com.qburst.bind.skillforge.quiz.presentation.theme.WhiteColor
import com.qburst.bind.skillforge.quiz.presentation.theme.loginTextForgeColor
import com.qburst.bind.skillforge.quiz.presentation.theme.loginTextSkillColor
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.lato_regular
import kotlinproject.composeapp.generated.resources.text_login_forge
import kotlinproject.composeapp.generated.resources.text_login_skill
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun DualTitle(
    textSize: TextUnit,
    isFromHome: Boolean = false
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(Res.string.text_login_skill),
            fontFamily = FontFamily(
                Font(
                    resource = Res.font.lato_regular,
                    weight = FontWeight.Bold,
                    style = FontStyle.Normal
                )
            ),
            color = if (isFromHome) WhiteColor else loginTextSkillColor,
            fontSize = textSize
        )

        Spacer(modifier = Modifier.width(SpacerSize.size_4))

        Text(
            text = stringResource(Res.string.text_login_forge),
            fontFamily = FontFamily(
                Font(
                    resource = Res.font.lato_regular,
                    weight = FontWeight.Bold,
                    style = FontStyle.Normal
                )
            ),
            color = loginTextForgeColor,
            fontSize = textSize
        )
    }
}