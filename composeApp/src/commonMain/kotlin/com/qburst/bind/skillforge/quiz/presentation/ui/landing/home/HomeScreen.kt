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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qburst.bind.skillforge.quiz.presentation.components.CenterAppTopBar
import com.qburst.bind.skillforge.quiz.presentation.components.DualTitle
import com.qburst.bind.skillforge.quiz.presentation.components.HomeTopicItem
import com.qburst.bind.skillforge.quiz.presentation.theme.PrimaryColor
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
    val topCornerRadius = 24.dp
    val viewModel: HomeViewModel = koinInject()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAppTopBar(
                title = {
                    DualTitle(
                        textSize = 24.sp,
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
                        modifier = Modifier.size(32.dp)
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    LazyColumn {
                        item {
                            Card(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = gray_background
                                ),
                                elevation = CardDefaults.cardElevation(6.dp),
                                shape = RoundedCornerShape(
                                    topEnd = topCornerRadius,
                                    bottomStart = topCornerRadius
                                )
                            ) {
                                Column {
                                    Text(
                                        text = stringResource(Res.string.app_name),
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                        modifier = Modifier.padding(
                                            start = 16.dp, top = 16.dp, end = 16.dp
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
                                        modifier = Modifier.padding(all = 16.dp),
                                        fontSize = 16.sp,
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
                                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(Res.string.upgrade_your_skills),
                                    fontSize = 20.sp,
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
                                    fontSize = 16.sp,
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
                            Box(modifier = Modifier.padding(bottom = 8.dp))
                        }
                    }
                    /*LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
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