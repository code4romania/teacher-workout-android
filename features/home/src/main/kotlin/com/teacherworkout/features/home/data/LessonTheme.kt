package com.teacherworkout.features.home.data

import androidx.annotation.DrawableRes
import com.teacherworkout.features.home.R

data class LessonTheme(
    @DrawableRes val imageResourceId: Int,
    val title: String
)

object HomeData {
    var dummyLessonThemes = listOf(
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
}