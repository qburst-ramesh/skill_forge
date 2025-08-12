package com.qburst.bind.skillforge.quiz.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.qburst.bind.skillforge.quiz.presentation.theme.FontSize
import com.qburst.bind.skillforge.quiz.presentation.theme.PrimaryColor
import com.qburst.bind.skillforge.quiz.presentation.theme.SpacerSize
import com.qburst.bind.skillforge.quiz.presentation.theme.WhiteColor
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.lato_regular
import org.jetbrains.compose.resources.Font

@Composable
fun DefaultPrimaryButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = SpacerSize.size_16,
                end = SpacerSize.size_16,
                top = SpacerSize.size_8,
                bottom = SpacerSize.size_16
            ),
        shape = RoundedCornerShape(SpacerSize.size_5),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor,
            contentColor = WhiteColor
        ),
        content = {
            Text(
                text = text,
                fontSize = FontSize.size_16,
                fontFamily = FontFamily(
                    Font(
                        resource = Res.font.lato_regular,
                        weight = FontWeight.Bold,
                        style = FontStyle.Normal
                    )
                ),
                maxLines = 1,
            )
        }
    )
}