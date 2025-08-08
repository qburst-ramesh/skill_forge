package com.qburst.bind.skillforge.quiz.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import coil3.compose.AsyncImage
import com.qburst.bind.skillforge.quiz.domain.model.BadgeData
import com.qburst.bind.skillforge.quiz.domain.model.BadgeLevel
import com.qburst.bind.skillforge.quiz.domain.model.TopicData
import com.qburst.bind.skillforge.quiz.presentation.theme.FontSize
import com.qburst.bind.skillforge.quiz.presentation.theme.PrimaryColor
import com.qburst.bind.skillforge.quiz.presentation.theme.SpacerSize
import com.qburst.bind.skillforge.quiz.presentation.theme.WhiteColor
import com.qburst.bind.skillforge.quiz.presentation.theme.topic_home_background
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.google_icon
import kotlinproject.composeapp.generated.resources.lato_regular
import kotlinproject.composeapp.generated.resources.place_holder
import kotlinproject.composeapp.generated.resources.start_quiz
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeTopicItem(
    topicData: TopicData,
    onClick: () -> Unit,
    isRow: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SpacerSize.size_16, vertical = SpacerSize.size_8),
        shape = RoundedCornerShape(size = SpacerSize.size_16),
        colors = CardDefaults.cardColors(containerColor = topic_home_background),
        elevation = CardDefaults.cardElevation(SpacerSize.size_4)
    ) {
        if (isRow) {
            Column {
                Row(
                    modifier = Modifier.padding(SpacerSize.size_16).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth(fraction = 0.2f),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.google_icon),
                            contentDescription = null,
                            modifier = Modifier.size(SpacerSize.size_40).wrapContentHeight(
                                align = Alignment.CenterVertically
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(SpacerSize.size_16))

                    Column {
                        Text(
                            text = topicData.name,
                            fontSize = FontSize.size_16,
                            maxLines = 2,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            overflow = Ellipsis
                        )

                        Box(modifier = Modifier.height(SpacerSize.size_8))

                        Text(
                            text = topicData.description,
                            fontSize = FontSize.size_16,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily.Default,
                            color = Color.Gray,
                            maxLines = 3,
                            overflow = Ellipsis
                        )
                    }
                }

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
                            text = stringResource(Res.string.start_quiz),
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
        } else {
            Column(
                modifier = Modifier.padding(SpacerSize.size_16).fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    topicData.badgeData?.let {
                        Card(
                            modifier = Modifier.align(Alignment.TopStart),
                            shape = RoundedCornerShape(SpacerSize.size_8),
                            colors = CardDefaults.cardColors(
                                containerColor = it.badgeLevel.backgroundColor
                            ),
                            elevation = CardDefaults.cardElevation(SpacerSize.size_0)
                        ) {
                            Text(
                                text = it.badgeLevel.level,
                                color = Color.Gray,
                                modifier = Modifier.padding(
                                    start = SpacerSize.size_16,
                                    end = SpacerSize.size_16,
                                    top = SpacerSize.size_4,
                                    bottom = SpacerSize.size_4
                                ),
                                fontSize = FontSize.size_13
                            )
                        }

                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = topicData.imageUrl,
                            contentDescription = topicData.name,
                            modifier = Modifier.size(SpacerSize.size_72),
                            placeholder = painterResource(Res.drawable.place_holder),
                            error = painterResource(Res.drawable.place_holder)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(SpacerSize.size_8))

                Text(
                    text = topicData.name,
                    fontSize = FontSize.size_16,
                    maxLines = 2,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    overflow = Ellipsis
                )

                Box(modifier = Modifier.height(SpacerSize.size_8))

                ReadMoreText(
                    text = topicData.description,
                    minimizedMaxLines = 2 // number of lines to show before "Read More"
                )

                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = SpacerSize.size_8),
                    shape = RoundedCornerShape(SpacerSize.size_5),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor,
                        contentColor = WhiteColor
                    ),
                    content = {
                        Text(
                            text = stringResource(Res.string.start_quiz),
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
        }
    }
}

@Composable
@Preview()
fun onHomeTopicItemPreview() {
    HomeTopicItem(
        topicData = TopicData(
            id = 1,
            name = "Kotlin Basics",
            description = "Learn the fundamentals of Kotlin programming language.",
            imageUrl = "https://example.com/kotlin.png",
            badgeData = BadgeData(
                badgeLevel = BadgeLevel.EXPERT,
                skillName = BadgeLevel.EXPERT.name,
            ),
            title = "Title",
            isCompleted = false,
            skill = "saojda"
        ),
        onClick = {}
    )
}
