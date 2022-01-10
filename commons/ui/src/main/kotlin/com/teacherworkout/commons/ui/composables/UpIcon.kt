package com.teacherworkout.features.account.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun UpIcon(
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    contentDescription: String? = null,
    onUpRequest: () -> Unit,
) {
    // TODO find a better way to handle up action from the toolbar!
    IconButton(onClick = onUpRequest) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = tint,
            contentDescription = contentDescription
        )
    }
}
