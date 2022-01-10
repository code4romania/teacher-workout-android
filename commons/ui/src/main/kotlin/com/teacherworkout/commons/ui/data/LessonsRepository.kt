package com.teacherworkout.commons.ui.data

import com.teacherworkout.commons.ui.model.Lesson
import com.teacherworkout.commons.ui.model.LessonTheme

interface LessonsRepository {
    suspend fun getAllLessonThemes(): Result<List<LessonTheme>>
    suspend fun getAllLessons(): Result<List<Lesson>>
    suspend fun getLesson(id: Long): Result<Lesson>
}
