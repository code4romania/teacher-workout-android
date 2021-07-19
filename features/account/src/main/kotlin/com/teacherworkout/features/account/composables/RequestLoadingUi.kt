package com.teacherworkout.features.account.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationLoadingUi(
    @StringRes loadingTextId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .background(Color(0xfff5f5f5), shape = RoundedCornerShape(8.dp)),
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Text(
            text = stringResource(id = loadingTextId),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, top = 16.dp)
        )
    }
}