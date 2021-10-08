package com.teacherworkout.features.account.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.teacherworkout.features.account.R

@Composable
fun ApplicationLogo(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.cd_logo)
    )
}
