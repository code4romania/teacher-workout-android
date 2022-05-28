package com.teacherworkout.commons.ui.model

import androidx.annotation.DrawableRes

data class LessonTheme(
    val id: String,
    val title: String,
    @DrawableRes val imageResourceId: Int
)
