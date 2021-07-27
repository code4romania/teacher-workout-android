package com.teacherworkout.features.account.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun UpIcon(onUpRequest: () -> Unit) {
    // TODO find a better way to handle up action from the toolbar!
    IconButton(onClick = onUpRequest) {
        Icon(Icons.Default.ArrowBack, contentDescription = "TODO text")
    }
}