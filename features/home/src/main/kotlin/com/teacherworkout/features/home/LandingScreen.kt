package com.teacherworkout.features.home

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
import com.teacherworkout.features.learn.R

@Composable
fun LandingScreen() {
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
                    text = stringResource(id = R.string.title_home),
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
fun OnLandingScreenPreview() {
    LandingScreen()
}