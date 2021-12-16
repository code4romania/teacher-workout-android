package com.teacherworkout.commons.ui.model

import androidx.annotation.DrawableRes

data class LessonTheme(
    @DrawableRes val imageResourceId: Int,
    val title: String,
    val subTitle: String? = "",
    val remainingTime: String? = ""
)
