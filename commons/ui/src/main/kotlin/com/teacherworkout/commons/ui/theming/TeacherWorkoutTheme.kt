package com.teacherworkout.commons.ui.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TeacherWorkoutTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    // TODO implement proper theme after figuring out the details about colors, typography, shapes etc
    MaterialTheme(colors = if (isDarkTheme) darkThemeColors else lightThemeColors, content = content)
}