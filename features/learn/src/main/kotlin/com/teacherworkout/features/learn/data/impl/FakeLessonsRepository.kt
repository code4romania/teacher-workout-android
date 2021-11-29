package com.teacherworkout.features.learn.data.impl

import com.teacherworkout.features.learn.R
import com.teacherworkout.features.learn.model.LessonTheme
import com.teacherworkout.features.learn.data.LessonsRepository
import com.teacherworkout.features.learn.data.Result

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
        return Result.Success(dummyLessonThemes)
    }
}
