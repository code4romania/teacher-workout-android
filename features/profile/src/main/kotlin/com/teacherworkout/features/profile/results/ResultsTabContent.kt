package com.teacherworkout.features.profile.results

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.teacherworkout.features.profile.R

const val COMPLETED_LESSONS = 16
const val TOTAL_LESSONS = 42

const val COMPLETED_LESSONS_1 = 10
const val COMPLETED_LESSONS_2 = 20

@Composable
fun ResultsTabContent(lessonsCategories: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            LessonRow(
                lessonCategoryText = stringResource(id = R.string.label_lessons_finished),
                COMPLETED_LESSONS,
                TOTAL_LESSONS
            ) {
                /* TODO */
            }
        }
        lessonsCategories.forEachIndexed { index, value ->
            item {
                LessonRow(
                    lessonCategoryText = value,
                    if (index == 2) COMPLETED_LESSONS_1 else COMPLETED_LESSONS_2,
                    TOTAL_LESSONS
                ) {
                    /* TODO */
                }
            }
        }
    }
}
