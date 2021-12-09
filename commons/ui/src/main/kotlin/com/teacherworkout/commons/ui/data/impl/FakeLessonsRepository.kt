package com.teacherworkout.commons.ui.data.impl

import com.teacherworkout.commons.ui.R
import com.teacherworkout.commons.ui.data.LessonsRepository
import com.teacherworkout.commons.ui.model.LessonTheme
import com.teacherworkout.commons.ui.data.Result
import kotlinx.coroutines.delay

class FakeLessonsRepository: LessonsRepository {
    val dummyLessonThemes = listOf(
            LessonTheme(
                R.drawable.art1,
                "Dictie si vorbire"
            ),
            LessonTheme(
                R.drawable.art2,
                "Interactiunea la clasa"
            ),
            LessonTheme(
                R.drawable.art3,
                "Gestionarea crizelor"
            ),
            LessonTheme(
                R.drawable.art4,
                "ADHD"
            ),
            LessonTheme(
                R.drawable.art1,
                "Incluziune"
            ),
            LessonTheme(
                R.drawable.art3,
                "Dizabilitati motorii"
            ),
            LessonTheme(
                R.drawable.art2,
                "Cum discuti despre boli psihice"
            ),
            LessonTheme(
                R.drawable.art1,
                "Dictie si vorbire"
            ),
            LessonTheme(
                R.drawable.art2,
                "Interactiunea la clasa"
            ),
            LessonTheme(
                R.drawable.art3,
                "Gestionarea crizelor"
            ),
            LessonTheme(
                R.drawable.art4,
                "ADHD"
            ),
            LessonTheme(
                R.drawable.art1,
                "Incluziune"
            ),
            LessonTheme(
                R.drawable.art3,
                "Dizabilitati motorii"
            ),
            LessonTheme(
                R.drawable.art2,
                "Cum discuti despre boli psihice"
            ),
        )

    override suspend fun getAllLessonThemes(): Result<List<LessonTheme>> {
        delay(1000)
        return Result.Success(dummyLessonThemes)
    }
}