package com.teacherworkout.commons.ui.theming

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val lightThemeColors = lightColors(
    primary = Color(0xff0d92b1),
    primaryVariant = Color(0xff006481),
    secondary = Color(0xffb12c0d),
    secondaryVariant = Color(0xff7a0000),
    onBackground = Color(0xffc1c1c1),
    onSurface = Color(0xffc1c1c1),
    error = Color(0xFFB00020),
    onError = Color.White,
)

// TODO at the moment we use the same colors for both light and dark themes, need to think the proper color combination
val darkThemeColors = lightColors(
    primary = Color(0xff0d92b1),
    primaryVariant = Color(0xff006481),
    secondary = Color(0xffb12c0d),
    secondaryVariant = Color(0xff7a0000),
    onBackground = Color(0xff0d92b1),
    onSurface = Color(0xff0d92b1),
)
