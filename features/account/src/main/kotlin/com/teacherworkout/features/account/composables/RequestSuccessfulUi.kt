package com.teacherworkout.features.account.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun RequestSuccessfulUi(
    @StringRes successTextId: Int,
    modifier: Modifier = Modifier, onSuccess: () -> Unit
) {
    Column(
        modifier = modifier.background(Color(0xfff5f5f5), shape = RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = successTextId),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        )
        Button(
            onClick = onSuccess,
            modifier = Modifier
                .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Sign In")
        }
    }
}