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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource

@Composable
fun RequestSuccessfulUi(
    @StringRes successTextId: Int,
    modifier: Modifier = Modifier,
    onSuccess: () -> Unit
) {
    val space8dp = dimensionResource(id =  com.teacherworkout.commons.ui.R.dimen.space_8dp)
    val space16dp = dimensionResource(id =  com.teacherworkout.commons.ui.R.dimen.space_16dp)
    Column(
        modifier = modifier.background(
            Color(color = 0xfff5f5f5),
            shape = RoundedCornerShape(space8dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = successTextId),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(
                top = space8dp,
                start = space16dp,
                end = space16dp,
                bottom = space8dp
            )
        )
        Button(
            onClick = onSuccess,
            modifier = Modifier
                .padding(bottom = space8dp, start = space16dp, end = space16dp)
                .fillMaxWidth()
        ) {
            Text("Sign In")
        }
    }
}
