package com.teacherworkout.features.learn.data

import com.teacherworkout.features.learn.model.LessonTheme

interface LessonsRepository {
    suspend fun getAllLessonThemes(): Result<List<LessonTheme>>
}
