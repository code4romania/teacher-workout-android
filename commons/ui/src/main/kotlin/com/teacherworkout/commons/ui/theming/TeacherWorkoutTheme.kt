package com.teacherworkout.commons.ui.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable

@Composable
fun TeacherWorkoutTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    // TODO implement proper theme after figuring out the details about colors, typography, shapes etc
    MaterialTheme(
        colors = if (isDarkTheme) darkThemeColors else lightThemeColors,
        shapes = Shapes(
            large = RoundedCornerShape(percent = 50)
        ),
        content = content
    )
}
