package com.teacherworkout.features.account.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource

@Composable
fun RequestFailedUi(
    @StringRes failureTextId: Int,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    val space8dp = dimensionResource(id = com.teacherworkout.android.R.dimen.space_8dp)
    val space16dp = dimensionResource(id = com.teacherworkout.android.R.dimen.space_16dp)
    Column(
        modifier = modifier.background(
            Color(0xfff5f5f5),
            shape = RoundedCornerShape(space8dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(space8dp))
        Text(
            text = stringResource(id = failureTextId),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(start = space16dp, end = space16dp)
        )
        Spacer(modifier = Modifier.height(space8dp))
        Button(
            onClick = onRetry,
            modifier = Modifier
                .padding(start = space16dp, end = space16dp)
                .fillMaxWidth()
        ) {
            Text("Retry")
        }
        Spacer(modifier = Modifier.height(space8dp))
    }
}