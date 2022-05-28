package com.teacherworkout.features.profile.landing

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.teacherworkout.features.profile.R

@Composable
fun ProfileTab(
    @StringRes textId: Int,
    isSelected: Boolean,
    onTabSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fadedGray = colorResource(id = R.color.dark_gray)
    Tab(
        selected = isSelected,
        onClick = onTabSelected,
        modifier = modifier,
        selectedContentColor = if (isSelected) MaterialTheme.colors.primary else fadedGray
    ) {
        Text(text = stringResource(id = textId), fontWeight = FontWeight.Bold)
    }
}
