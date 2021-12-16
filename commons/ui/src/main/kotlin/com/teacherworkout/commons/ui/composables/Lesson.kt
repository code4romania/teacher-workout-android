package com.teacherworkout.commons.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.teacherworkout.commons.ui.R
import com.teacherworkout.commons.ui.model.LessonTheme

fun LazyListScope.lessonCard(
    listTitle: Int,
    lessonThemes: List<LessonTheme>,
    onLessonThemeClick: (LessonTheme) -> Unit
) {
    item {
        Text(
            text = stringResource(id = listTitle),
            style = TextStyle(
                color = MaterialTheme.colors.primary,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
    }

    items(lessonThemes.chunked(1)) { lessons ->
        val space16dp = dimensionResource(id = R.dimen.space_16dp)

        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            @Composable
            fun LessonThemeCardUtil(themeIndex: Int) {
                val heightLessonThemeCard = dimensionResource(id = R.dimen.lesson_card_height)
                LessonCard(
                    modifier = Modifier
                        .height(heightLessonThemeCard)
                        .weight(1f),
                    lessonTheme = lessons[themeIndex],
                    onClick = { onLessonThemeClick(lessons[themeIndex]) }
                )
            }

            LessonThemeCardUtil(themeIndex = 0)
            Spacer(modifier = Modifier.width(space16dp))
        }
    }
}