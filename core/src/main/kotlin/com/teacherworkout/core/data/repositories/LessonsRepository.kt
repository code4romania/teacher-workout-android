package com.teacherworkout.core.data.repositories

import com.teacherworkout.core.data.Result
import com.teacherworkout.core.fragment.Lesson
import com.teacherworkout.core.fragment.LessonStatus
import com.teacherworkout.core.fragment.LessonTheme

interface LessonsRepository {
    suspend fun getLessonThemes(): Result<List<LessonTheme>>
    suspend fun getLessonStatuses(lessonThemeId: String?): Result<List<LessonStatus>>
    suspend fun getLessonStatus(id: String): Result<LessonStatus>
    suspend fun getLesson(id: String): Result<Lesson>
}
