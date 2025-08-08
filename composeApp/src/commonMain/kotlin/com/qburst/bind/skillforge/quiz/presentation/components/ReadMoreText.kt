package com.qburst.bind.skillforge.quiz.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import com.qburst.bind.skillforge.quiz.presentation.theme.FontSize
import com.qburst.bind.skillforge.quiz.presentation.theme.SpacerSize
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ReadMoreText(
    text: String,
    minimizedMaxLines: Int = 2 // number of lines to show before "Read More"
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isOverflowing by remember { mutableStateOf(false) }

    Column {
        Text(
            text = text,
            maxLines = if (isExpanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { layoutResult ->
                // detect if text exceeds maxLines
                if (!isExpanded) {
                    isOverflowing = layoutResult.hasVisualOverflow
                }
            },
            modifier = Modifier.animateContentSize(),
            fontSize = FontSize.size_16,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Default,
            color = Color.Black,
        )

        if (isOverflowing || isExpanded) {
            Text(
                text = if (isExpanded) "Read Less" else "Read More",
                modifier = Modifier.align(Alignment.End)
                    .padding(top = SpacerSize.size_8)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        isExpanded = !isExpanded
                    },
                color = Color.Blue,
                fontSize = FontSize.size_10,
            )
        }
    }
}

@Preview()
@Composable
fun onReadMoreTextPreview() {
    ReadMoreText(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        minimizedMaxLines = 2
    )
}
