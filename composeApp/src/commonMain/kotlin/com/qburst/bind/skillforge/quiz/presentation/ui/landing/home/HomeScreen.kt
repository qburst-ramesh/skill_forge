package com.qburst.bind.skillforge.quiz.presentation.ui.landing.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qburst.bind.skillforge.quiz.presentation.components.CenterAppTopBar
import com.qburst.bind.skillforge.quiz.presentation.components.DualTitle
import com.qburst.bind.skillforge.quiz.presentation.theme.PrimaryColor
import com.qburst.bind.skillforge.quiz.presentation.theme.gray_background
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.home_welcome_text
import kotlinproject.composeapp.generated.resources.lato_light
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen() {
    val topCornerRadius = 24.dp

    Scaffold(
        topBar = {
            CenterAppTopBar(
                title = {
                    DualTitle(textSize = 24.sp)
                }
            )
        }
    ) { defaultPadding ->
        Column(modifier = Modifier.padding(defaultPadding)) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp).fillMaxSize()
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = gray_background
                        ),
                        elevation = CardDefaults.cardElevation(6.dp),
                        shape = RoundedCornerShape(
                            topEnd = topCornerRadius,
                            bottomStart = topCornerRadius
                        )
                    ) {
                        Text(
                            text = stringResource(Res.string.home_welcome_text),
                            modifier = Modifier.padding(all = 16.dp),
                            fontSize = 20.sp,
                            color = PrimaryColor,
                            fontFamily = FontFamily(
                                Font(
                                    resource = Res.font.lato_light,
                                    weight = FontWeight.Bold,
                                    style = FontStyle.Normal
                                )
                            ),
                        )
                    }
                }
            }
        }
    }
}