package com.teacherworkout.commons.ui.data.impl

import com.teacherworkout.commons.ui.R
import com.teacherworkout.commons.ui.data.LessonsRepository
import com.teacherworkout.commons.ui.data.Result
import com.teacherworkout.commons.ui.model.Lesson
import com.teacherworkout.commons.ui.model.LessonTheme
import kotlinx.coroutines.delay

class FakeLessonsRepository : LessonsRepository {
    private val dummyLessons = listOf(
        Lesson(
            1,
            "Dictie si vorbire",
            "Cum discuți despre boli psihice",
            R.drawable.art1,
            10,
            9
        ),
        Lesson(
            2,
            "Interactiunea la clasa",
            "Interacțiunea la clasă",
            R.drawable.art2,
            10,
            4
        ),
        Lesson(
            3,
            "Gestionarea crizelor",
            "Interacțiunea la clasă",
            R.drawable.art3,
            10,
            1
        ),
    )
    val dummyLessonThemes = listOf(
        LessonTheme(
            "1",
            "ADHD",
            R.drawable.art4
        ),
        LessonTheme(
            "2",
            "Incluziune",
            R.drawable.art1
        ),
        LessonTheme(
            "3",
            "Dizabilitati motorii",
            R.drawable.art3
        ),
        LessonTheme(
            "4",
            "Cum discuti despre boli psihice",
            R.drawable.art2
        ),
        LessonTheme(
            "5",
            "Dictie si vorbire",
            R.drawable.art1
        ),
        LessonTheme(
            "6",
            "Interactiunea la clasa",
            R.drawable.art2
        ),
        LessonTheme(
            "7",
            "Gestionarea crizelor",
            R.drawable.art3
        ),
        LessonTheme(
            "8",
            "ADHD",
            R.drawable.art4
        ),
        LessonTheme(
            "9",
            "Incluziune",
            R.drawable.art1
        ),
        LessonTheme(
            "10",
            "Dizabilitati motorii",
            R.drawable.art3
        ),
        LessonTheme(
            "11",
            "Cum discuti despre boli psihice",
            R.drawable.art2
        ),
    )

    override suspend fun getAllLessons(): Result<List<Lesson>> {
        delay(timeMillis = 800)
        return Result.Success(dummyLessons)
    }

    override suspend fun getAllLessonThemes(): Result<List<LessonTheme>> {
        delay(timeMillis = 1000)
        return Result.Success(dummyLessonThemes)
    }

    override suspend fun getLesson(id: Long) =
        try {
            dummyLessons.first { lesson -> lesson.id == id }.let {
                Result.Success(it)
            }
        } catch(e: Exception) {
            Result.Error(e)
        }
}
