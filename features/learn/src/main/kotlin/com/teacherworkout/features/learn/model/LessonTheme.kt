package com.teacherworkout.features.learn.model

import androidx.annotation.DrawableRes

data class LessonTheme(
    @DrawableRes val imageResourceId: Int,
    val title: String
)
