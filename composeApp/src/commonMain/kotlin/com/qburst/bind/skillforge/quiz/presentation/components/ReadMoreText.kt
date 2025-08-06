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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ReadMoreText(
    text: String,
    minimizedMaxLines: Int = 3 // number of lines to show before "Read More"
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
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Default,
            color = Color.Black,
        )

        if (isOverflowing || isExpanded) {
            Text(
                text = if (isExpanded) "Read Less" else "Read More",
                modifier = Modifier.align(Alignment.End)
                    .padding(top = 8.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        isExpanded = !isExpanded
                    },
                color = Color.Blue,
                fontSize = 10.sp,
            )
        }
    }
}

