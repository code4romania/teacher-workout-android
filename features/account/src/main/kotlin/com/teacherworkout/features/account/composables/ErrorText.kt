package com.teacherworkout.features.account.composables

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit

@Composable
fun ErrorText(
    @StringRes errorTextId: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.error,
    fontSize: TextUnit = MaterialTheme.typography.caption.fontSize
) {
    Text(
        text = stringResource(id = errorTextId),
        color = color,
        fontSize = fontSize,
        fontStyle = FontStyle.Italic,
        modifier = modifier
    )
}