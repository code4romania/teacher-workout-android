package com.teacherworkout.features.account.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun AccountScreenScaffold(
    @StringRes titleId: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
                UpIcon { navController.popBackStack() }
                Text(stringResource(id = titleId))
            }
        },
        content = content,
        modifier = modifier
    )
}