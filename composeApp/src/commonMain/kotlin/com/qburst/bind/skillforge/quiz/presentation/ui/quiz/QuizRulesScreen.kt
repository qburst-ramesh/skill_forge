package com.qburst.bind.skillforge.quiz.presentation.ui.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.qburst.bind.skillforge.quiz.domain.model.QuizDifficultyLevel
import com.qburst.bind.skillforge.quiz.presentation.components.CenterAppTopBar
import com.qburst.bind.skillforge.quiz.presentation.components.DefaultPrimaryButton
import com.qburst.bind.skillforge.quiz.presentation.theme.Black_color
import com.qburst.bind.skillforge.quiz.presentation.theme.FontSize
import com.qburst.bind.skillforge.quiz.presentation.theme.PrimaryColor
import com.qburst.bind.skillforge.quiz.presentation.theme.SpacerSize
import com.qburst.bind.skillforge.quiz.presentation.theme.WhiteColor
import com.qburst.bind.skillforge.quiz.presentation.theme.gray_background
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.difficulty_level_button
import kotlinproject.composeapp.generated.resources.drop_down_arrow
import kotlinproject.composeapp.generated.resources.lato_bold
import kotlinproject.composeapp.generated.resources.lato_regular
import kotlinproject.composeapp.generated.resources.quiz_rules_agree_request
import kotlinproject.composeapp.generated.resources.quiz_rules_dialog_confirm_button
import kotlinproject.composeapp.generated.resources.quiz_rules_dialog_select_difficulty_title
import kotlinproject.composeapp.generated.resources.quiz_rules_dialog_title
import kotlinproject.composeapp.generated.resources.quiz_rules_title
import kotlinproject.composeapp.generated.resources.start_quiz
import kotlinproject.composeapp.generated.resources.title_description
import kotlinproject.composeapp.generated.resources.title_topic
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun QuizRulesScreen() {
    // Placeholder for Quiz Rules Screen UI
    // This will be implemented later

    val viewModel: QuizViewModel = koinInject()
    val state by viewModel.uiEvent.collectAsState()
    var checked by remember { mutableStateOf(false) }
    var showPopup by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    var selectedOption by remember { mutableStateOf(state.difficultyLevel) }

    LaunchedEffect(Unit) {
        showPopup = true
    }

    Scaffold(
        topBar = {
            CenterAppTopBar(
                title = {
                    Text(
                        text = stringResource(Res.string.quiz_rules_title),
                        fontSize = FontSize.size_24,
                        maxLines = 1
                    )
                },
            )
        },
    ) { defaultPadding ->

        if (showPopup) {
            PopupWithDropdownDemo(
                showDialog = showPopup,
                onClick = {
                    selectedOption = it
                    viewModel.onEvent(QuizUiEvent.OnDifficultyLevelSelected(it))
                    showPopup = false
                },
                selectedOption = selectedOption
            )
        }

        LazyColumn(
            modifier = Modifier.padding(
                top = defaultPadding.calculateTopPadding(),
                bottom = defaultPadding.calculateBottomPadding()
            ).fillMaxSize()
        ) {
            item {
                Card(
                    modifier = Modifier.padding(all = SpacerSize.size_16).fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = gray_background
                    ),
                    elevation = CardDefaults.cardElevation(SpacerSize.size_6),
                    shape = RoundedCornerShape(SpacerSize.size_16)
                ) {
                    Column(
                        modifier = Modifier.padding(all = SpacerSize.size_16)
                    ) {
                        QuizCardTitleAndDescription(
                            title = stringResource(Res.string.title_topic),
                            description = stringResource(Res.string.title_description)
                        )

                        QuizCardTitleAndDescription(
                            title = stringResource(Res.string.title_topic),
                            description = stringResource(Res.string.title_description)
                        )

                        QuizCardTitleAndDescription(
                            title = stringResource(Res.string.title_topic),
                            description = stringResource(Res.string.title_description),
                            isDifficultLevel = true,
                            level = state.difficultyLevel
                        )

                        Button(
                            onClick = {
                                showPopup = true
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(SpacerSize.size_5),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent, contentColor = WhiteColor
                            ),
                            border = BorderStroke(
                                width = SpacerSize.size_1, color = Color.Gray
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = SpacerSize.size_0
                            ),
                            content = {
                                Text(
                                    text = stringResource(Res.string.difficulty_level_button),
                                    fontSize = FontSize.size_16,
                                    fontFamily = FontFamily(
                                        Font(
                                            resource = Res.font.lato_regular,
                                            weight = FontWeight.Bold,
                                            style = FontStyle.Normal
                                        )
                                    ),
                                    maxLines = 1,
                                    color = Color.Black
                                )
                            })
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(
                        start = SpacerSize.size_16,
                        bottom = SpacerSize.size_16,
                        end = SpacerSize.size_16
                    ),
                    text = stringResource(Res.string.quiz_rules_title),
                    fontSize = FontSize.size_24,
                    fontFamily = FontFamily(
                        Font(
                            resource = Res.font.lato_regular,
                            weight = FontWeight.Bold,
                            style = FontStyle.Normal
                        )
                    ),
                    color = PrimaryColor
                )
            }

            items(state.quizRulesData.rulesList) { rules ->
                RulesView(
                    rules
                )
            }

            item {
                Column {
                    Box(modifier = Modifier.height(SpacerSize.size_8))

                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(
                            end = SpacerSize.size_16
                        ).fillMaxWidth().clickable {
                            checked = !checked
                        }) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = it },
                            modifier = Modifier.padding(start = SpacerSize.size_4)
                        )
                        Text(
                            text = stringResource(Res.string.quiz_rules_agree_request),
                            modifier = Modifier.padding(start = SpacerSize.size_8)
                        )
                    }

                    if (!state.isUserAcceptedRules) {
                        SnackbarHost(
                            hostState = snackBarHostState,
                            snackbar = { data -> Snackbar { Text(data.visuals.message) } })
                    }

                    Button(
                        onClick = {
                            viewModel.onEvent(QuizUiEvent.OnStartQuiz(isAccepted = checked))
                        },
                        modifier = Modifier.fillMaxWidth().padding(
                            start = SpacerSize.size_16,
                            end = SpacerSize.size_16,
                            top = SpacerSize.size_8,
                            bottom = SpacerSize.size_16
                        ),
                        shape = RoundedCornerShape(SpacerSize.size_5),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryColor, contentColor = WhiteColor
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
                        })
                }
            }
        }
    }
}

@Composable
fun QuizCardTitleAndDescription(
    title: String,
    description: String,
    isDifficultLevel: Boolean = false,
    level: QuizDifficultyLevel = QuizDifficultyLevel.DEFAULT
) {
    Text(
        text = title, fontSize = FontSize.size_14, fontFamily = FontFamily(
            Font(
                resource = Res.font.lato_regular, weight = FontWeight.Bold, style = FontStyle.Normal
            )
        ), color = Black_color
    )

    Box(modifier = Modifier.height(SpacerSize.size_8))

    if (isDifficultLevel) {
        Card(
            shape = RoundedCornerShape(SpacerSize.size_8), colors = CardDefaults.cardColors(
                containerColor = level.backgroundColor
            ), elevation = CardDefaults.cardElevation(SpacerSize.size_0)
        ) {
            Text(
                text = level.level, color = Color.Gray, modifier = Modifier.padding(
                    start = SpacerSize.size_16,
                    end = SpacerSize.size_16,
                    top = SpacerSize.size_4,
                    bottom = SpacerSize.size_4
                ), fontSize = FontSize.size_13
            )
        }
    } else {
        Text(
            text = description, fontSize = FontSize.size_14, fontFamily = FontFamily(
                Font(
                    resource = Res.font.lato_regular,
                    weight = FontWeight.Bold,
                    style = FontStyle.Normal
                )
            ), color = Black_color
        )
    }

    Box(modifier = Modifier.height(SpacerSize.size_16))
}

@Composable
fun RulesView(rules: String) {
    Card(
        modifier = Modifier.padding(
            start = SpacerSize.size_16,
            end = SpacerSize.size_16,
            top = SpacerSize.size_4,
            bottom = SpacerSize.size_4
        ).fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = gray_background
        ),
        elevation = CardDefaults.cardElevation(SpacerSize.size_1),
        shape = RoundedCornerShape(SpacerSize.size_8)
    ) {
        Text(
            text = rules, fontSize = FontSize.size_14, fontFamily = FontFamily(
                Font(
                    resource = Res.font.lato_bold,
                    weight = FontWeight.Bold,
                    style = FontStyle.Normal
                )
            ), color = Black_color, modifier = Modifier.padding(SpacerSize.size_16)
        )
    }
}

@Composable
fun PopupWithDropdownDemo(
    showDialog: Boolean,
    onClick: (QuizDifficultyLevel) -> Unit,
    selectedOption: QuizDifficultyLevel
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(selectedOption) }
    var isLevelChoose by remember { mutableStateOf(false) }

    val options = QuizDifficultyLevel.getAllLevels()

    Dialog(onDismissRequest = { showDialog }) {
        Box(
            modifier = Modifier.background(Color.White, RoundedCornerShape(SpacerSize.size_8))
                .padding(top = SpacerSize.size_16)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(SpacerSize.size_8)
            ) {
                Text(
                    text = stringResource(Res.string.quiz_rules_dialog_title),
                    color = Color.Black,
                    fontSize = FontSize.size_24,
                    fontFamily = FontFamily(
                        Font(
                            resource = Res.font.lato_regular,
                            weight = FontWeight.Bold,
                            style = FontStyle.Normal
                        )
                    ),
                    modifier = Modifier.padding(horizontal = SpacerSize.size_16)
                )

                Text(
                    text = stringResource(Res.string.quiz_rules_dialog_select_difficulty_title),
                    color = Color.Black,
                    fontSize = FontSize.size_13,
                    fontFamily = FontFamily(
                        Font(
                            resource = Res.font.lato_regular,
                            weight = FontWeight.Normal,
                            style = FontStyle.Normal
                        )
                    ),
                    modifier = Modifier.padding(horizontal = SpacerSize.size_16)
                )

                // Dropdown
                Box(
                    modifier = Modifier.padding(horizontal = SpacerSize.size_16)
                ) {
                    OutlinedButton(
                        onClick = { expanded = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(SpacerSize.size_5),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = selectedOption.level,
                                color = Color.Black,
                                fontSize = FontSize.size_13,
                                fontFamily = FontFamily(
                                    Font(
                                        resource = Res.font.lato_regular,
                                        weight = FontWeight.Normal,
                                        style = FontStyle.Normal
                                    )
                                ),
                                modifier = Modifier.padding(horizontal = SpacerSize.size_16)
                            )

                            Icon(
                                painter = painterResource(Res.drawable.drop_down_arrow),
                                contentDescription = "Dropdown Arrow"
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = expanded, onDismissRequest = { expanded = false }) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = option.level,
                                        color = Color.Black,
                                        fontSize = FontSize.size_13,
                                        fontFamily = FontFamily(
                                            Font(
                                                resource = Res.font.lato_regular,
                                                weight = FontWeight.Normal,
                                                style = FontStyle.Normal
                                            )
                                        ),
                                        modifier = Modifier.padding(horizontal = SpacerSize.size_16)
                                    )
                                },
                                onClick = {
                                    selectedOption = option
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                if (isLevelChoose) {
                    Text(
                        text = stringResource(Res.string.quiz_rules_dialog_select_difficulty_title),
                        color = Color.Red,
                        fontSize = FontSize.size_10,
                        fontFamily = FontFamily(
                            Font(
                                resource = Res.font.lato_regular,
                                weight = FontWeight.Normal,
                                style = FontStyle.Normal
                            )
                        ),
                        modifier = Modifier.padding(horizontal = SpacerSize.size_16)
                    )
                }

                DefaultPrimaryButton(
                    text = stringResource(Res.string.quiz_rules_dialog_confirm_button),
                    onClick = {
                        if (selectedOption == QuizDifficultyLevel.DEFAULT) {
                            isLevelChoose = true
//                            return@DefaultPrimaryButton
                        } else {
                            onClick(selectedOption)
                        }
                    },
                )
            }
        }
    }
}
