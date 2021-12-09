package com.teacherworkout.features.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LandingScreen() {
    Text(
        text = stringResource(id = R.string.title_themes),
        style = TextStyle(
            color = MaterialTheme.colors.primary,
            fontSize = MaterialTheme.typography.h5.fontSize,
            fontWeight = FontWeight.Bold
        )
    )
}

@Preview
@Composable
fun OnLandingScreenPreview() {
    LandingScreen()
}
