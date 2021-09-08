package com.teacherworkout.features.profile

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ExperienceLevelText(level: Int) {
    Text(
        text = stringResource(id = R.string.experience_level, level.toString()),
        letterSpacing = MaterialTheme.typography.h4.letterSpacing,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary,
        fontSize = MaterialTheme.typography.h4.fontSize
    )
}
