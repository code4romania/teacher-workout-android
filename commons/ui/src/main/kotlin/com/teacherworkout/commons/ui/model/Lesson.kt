package com.teacherworkout.commons.ui.model

import androidx.annotation.DrawableRes

//TODO: refactor to match the model from the GraphQL
data class Lesson(
    val id: Long,
    val title: String,
    val lessonThemeTitle: String,
    @DrawableRes val imageResourceId: Int,
    val durationInMinutes: Int,
    val remainingMinutes: Int,
    val started: Boolean = false,
    val saved: Boolean = false
)
