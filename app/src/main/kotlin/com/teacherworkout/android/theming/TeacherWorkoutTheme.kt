package com.teacherworkout.android.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.teacherworkout.android.R

@Composable
fun TeacherWorkoutTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit

) {
    // TODO implement proper theme after figuring out the details about colors, typography, shapes etc
    val mulish = FontFamily(
        Font(R.font.mulish_regular),
    )
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = mulish,
            fontWeight = FontWeight.Normal,
        ),
        h1 = TextStyle(
            fontFamily = mulish,
            fontWeight = FontWeight.Bold,
        ),
        h2 = TextStyle(
            fontFamily = mulish,
            fontWeight = FontWeight.Thin,
        ),
        h3 = TextStyle(
            fontFamily = mulish,
            fontStyle = FontStyle.Italic,
        ),
        body2 = TextStyle(
            fontFamily = mulish,
            fontWeight = FontWeight.Light,
        )

    )

    MaterialTheme(
        colors = if (isDarkTheme) darkThemeColors else lightThemeColors,
        content = content,
        typography = typography
    )
}