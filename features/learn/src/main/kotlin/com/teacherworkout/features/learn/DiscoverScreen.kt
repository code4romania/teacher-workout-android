package com.teacherworkout.features.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DiscoverScreen() {
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val backgroundColor = colorResource(id = R.color.landing_background)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(backgroundColor)
                    .padding(space16dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.title_discover),
                    fontSize = MaterialTheme.typography.button.fontSize,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.alignByBaseline()
                )
            }
        }
    }
}

@Preview
@Composable
fun OnDiscoverScreenPreview() {
    DiscoverScreen()
}
