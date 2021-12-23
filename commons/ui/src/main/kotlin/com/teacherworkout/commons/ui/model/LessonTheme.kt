package com.teacherworkout.commons.ui.model

import androidx.annotation.DrawableRes

//TODO: refactor to match the model from the GraphQL
data class LessonTheme(
    val id: Long,
    val title: String,
    @DrawableRes val imageResourceId: Int
)
