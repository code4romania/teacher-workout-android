package com.teacherworkout.features.profile.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.teacherworkout.features.profile.R

@Composable
fun SettingsRow(text: String, onSelection: (String) -> Unit) {
    val minSettingRowHeight = dimensionResource(id = R.dimen.min_setting_row_height)
    Column {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minSettingRowHeight)
                .clickable { onSelection(text) }
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        }
        Divider()
    }
}
