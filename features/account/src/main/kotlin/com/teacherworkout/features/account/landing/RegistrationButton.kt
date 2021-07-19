package com.teacherworkout.features.account.landing

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.teacherworkout.features.account.R

@Composable
fun RegistrationButton(modifier: Modifier = Modifier, onRegistrationRequest: () -> Unit) {
    Button(
        onClick = onRegistrationRequest, modifier = modifier
    ) {
        Text(stringResource(id = R.string.landing_btn_register), fontSize = 18.sp)
    }
}