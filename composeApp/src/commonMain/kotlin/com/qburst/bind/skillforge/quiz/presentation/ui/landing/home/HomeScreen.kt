package com.qburst.bind.skillforge.quiz.presentation.ui.landing.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import com.qburst.bind.skillforge.quiz.presentation.components.CenterAppTopBar
import com.qburst.bind.skillforge.quiz.presentation.components.DualTitle
import com.qburst.bind.skillforge.quiz.presentation.components.HomeTopicItem
import com.qburst.bind.skillforge.quiz.presentation.theme.FontSize
import com.qburst.bind.skillforge.quiz.presentation.theme.PrimaryColor
import com.qburst.bind.skillforge.quiz.presentation.theme.SpacerSize
import com.qburst.bind.skillforge.quiz.presentation.theme.gray_background
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.app_name
import kotlinproject.composeapp.generated.resources.explore
import kotlinproject.composeapp.generated.resources.home_welcome_text
import kotlinproject.composeapp.generated.resources.lato_bold
import kotlinproject.composeapp.generated.resources.lato_regular
import kotlinproject.composeapp.generated.resources.upgrade_your_skills
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun HomeScreen() {
    val topCornerRadius = SpacerSize.size_24
    val viewModel: HomeViewModel = koinInject()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAppTopBar(
                title = {
                    DualTitle(
                        textSize = FontSize.size_24,
                        isFromHome = true
                    )
                }
            )
        },
        containerColor = Color.White
    ) { defaultPadding ->

        Column(
            modifier = Modifier.padding(
                start = defaultPadding.calculateStartPadding(
                    LayoutDirection.Ltr
                ),
                end = defaultPadding.calculateStartPadding(
                    LayoutDirection.Rtl
                ),
                top = defaultPadding.calculateTopPadding(),
            )
        ) {
            if (state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(SpacerSize.size_32)
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    LazyColumn {
                        item {
                            Card(
                                modifier = Modifier.padding(
                                    horizontal = SpacerSize.size_16,
                                    vertical = SpacerSize.size_16
                                )
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = gray_background
                                ),
                                elevation = CardDefaults.cardElevation(SpacerSize.size_6),
                                shape = RoundedCornerShape(
                                    topEnd = topCornerRadius,
                                    bottomStart = topCornerRadius
                                )
                            ) {
                                Column {
                                    Text(
                                        text = stringResource(Res.string.app_name),
                                        fontSize = FontSize.size_20,
                                        color = Color.Black,
                                        modifier = Modifier.padding(
                                            start = SpacerSize.size_16,
                                            top = SpacerSize.size_16,
                                            end = SpacerSize.size_16
                                        ),
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = Res.font.lato_bold,
                                                weight = FontWeight.Bold,
                                                style = FontStyle.Normal
                                            )
                                        ),
                                    )
                                    Text(
                                        text = stringResource(Res.string.home_welcome_text),
                                        modifier = Modifier.padding(all = SpacerSize.size_16),
                                        fontSize = FontSize.size_16,
                                        color = PrimaryColor,
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = Res.font.lato_regular,
                                                weight = FontWeight.Bold,
                                                style = FontStyle.Normal
                                            )
                                        ),
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(
                                        start = SpacerSize.size_16,
                                        end = SpacerSize.size_16,
                                        bottom = SpacerSize.size_16
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(Res.string.upgrade_your_skills),
                                    fontSize = FontSize.size_20,
                                    color = PrimaryColor,
                                    fontFamily = FontFamily(
                                        Font(
                                            resource = Res.font.lato_bold,
                                            weight = FontWeight.Normal,
                                            style = FontStyle.Normal
                                        )
                                    ),
                                )

                                Text(
                                    text = stringResource(Res.string.explore),
                                    fontSize = FontSize.size_16,
                                    color = Color.Blue,
                                    fontFamily = FontFamily(
                                        Font(
                                            resource = Res.font.lato_regular,
                                            weight = FontWeight.Normal,
                                            style = FontStyle.Normal
                                        )
                                    ),
                                )
                            }
                        }

                        val data = state.homeData?.topicList ?: emptyList()

                        items(data) { topic ->
                            topic?.let {
                                HomeTopicItem(
                                    topicData = it,
                                    isRow = false,
                                    onClick = {

                                    }
                                )
                            }
                        }

                        item {
                            Box(modifier = Modifier.padding(bottom = SpacerSize.size_8))
                        }
                    }
                    /*LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(SpacerSize.size_8),
                        verticalArrangement = Arrangement.spacedBy(SpacerSize.size_8),
                        horizontalArrangement = Arrangement.spacedBy(SpacerSize.size_8)
                    ) {

                        val data = state.homeData?.topicList ?: emptyList()

                        this.items(data.size) {
                            data[it]?.let { topic ->
                                HomeTopicItem(
                                    topicData = topic,
                                    onClick = {

                                    }
                                )
                            }
                        }
                    }*/
                }
            }
        }
    }
}