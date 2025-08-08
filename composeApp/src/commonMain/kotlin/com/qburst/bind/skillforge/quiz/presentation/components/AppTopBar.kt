package com.qburst.bind.skillforge.quiz.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.qburst.bind.skillforge.quiz.presentation.theme.App_bar_background_color
import com.qburst.bind.skillforge.quiz.presentation.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String?,
    action: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            title?.let {
                Text(
                    text = it, color = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryColor,
            titleContentColor = Color.White
        ),
        actions = action
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAppTopBar(
    title: @Composable () -> Unit,
    action: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = title,
        actions = action,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = App_bar_background_color,
            titleContentColor = Color.White
        ),
    )
}
