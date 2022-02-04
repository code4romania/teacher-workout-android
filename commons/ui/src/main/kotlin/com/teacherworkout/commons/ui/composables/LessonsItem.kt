package com.teacherworkout.commons.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.teacherworkout.commons.ui.R
import com.teacherworkout.core.fragment.Lesson
import com.teacherworkout.core.fragment.LessonStatus

fun LazyListScope.lessonsItem(
    listTitle: Int,
    lessonStatuses: List<LessonStatus>,
    onLessonClick: (Lesson) -> Unit
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

    items(lessonStatuses) { lessonStatus ->
        val heightLessonCard = dimensionResource(id = R.dimen.lesson_card_height)
        LessonCard(
            modifier = Modifier.height(heightLessonCard),
            lessonStatus = lessonStatus,
            onClick = { onLessonClick(lessonStatus.lesson) }
        )
    }
}
