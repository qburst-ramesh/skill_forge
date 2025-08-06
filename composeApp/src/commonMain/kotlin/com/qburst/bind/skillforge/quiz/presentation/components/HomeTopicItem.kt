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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.qburst.bind.skillforge.quiz.domain.model.TopicData
import com.qburst.bind.skillforge.quiz.presentation.theme.PrimaryColor
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

@Composable
fun HomeTopicItem(
    topicData: TopicData,
    onClick: () -> Unit,
    isRow: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.cardColors(containerColor = topic_home_background),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        if (isRow) {
            Column {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth(fraction = 0.2f),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.google_icon),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp).wrapContentHeight(
                                align = Alignment.CenterVertically
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = topicData.name,
                            fontSize = 16.sp,
                            maxLines = 2,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            overflow = Ellipsis
                        )

                        Box(modifier = Modifier.height(8.dp))

                        Text(
                            text = topicData.description,
                            fontSize = 16.sp,
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
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor,
                        contentColor = WhiteColor
                    ),
                    content = {
                        Text(
                            text = stringResource(Res.string.start_quiz),
                            fontSize = 16.sp,
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
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    topicData.badgeData?.let {
                        Card(
                            modifier = Modifier.align(Alignment.TopStart),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = it.badgeLevel.backgroundColor
                            ),
                            elevation = CardDefaults.cardElevation(0.dp)
                        ) {
                            Text(
                                text = it.badgeLevel.level,
                                color = Color.Gray,
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 4.dp,
                                    bottom = 4.dp
                                ),
                                fontSize = 13.sp
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
                            modifier = Modifier.size(72.dp),
                            placeholder = painterResource(Res.drawable.place_holder),
                            error = painterResource(Res.drawable.place_holder)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = topicData.name,
                    fontSize = 16.sp,
                    maxLines = 2,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    overflow = Ellipsis
                )

                Box(modifier = Modifier.height(8.dp))

                ReadMoreText(
                    text = topicData.description,
                    minimizedMaxLines = 2 // number of lines to show before "Read More"
                )

                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor,
                        contentColor = WhiteColor
                    ),
                    content = {
                        Text(
                            text = stringResource(Res.string.start_quiz),
                            fontSize = 16.sp,
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
