package com.teacherworkout.commons.ui.data

import com.teacherworkout.commons.ui.model.LessonTheme

interface LessonsRepository {
    suspend fun getAllLessonThemes(): Result<List<LessonTheme>>
}
