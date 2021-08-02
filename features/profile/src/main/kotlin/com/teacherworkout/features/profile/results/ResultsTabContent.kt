package com.teacherworkout.features.profile.results

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.teacherworkout.features.profile.R

@Composable
fun ResultsTabContent(lessonsCategories: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            LessonRow(lessonCategoryText = stringResource(id = R.string.label_lessons_finished), 16, 210) {
                /* TODO */
            }
        }
        lessonsCategories.forEachIndexed { index, value ->
            item {
                LessonRow(lessonCategoryText = value, if (index == 2) 41 else 26, 42) {
                    /* TODO */
                }
            }
        }
    }
}
