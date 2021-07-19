package com.teacherworkout.features.account.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.teacherworkout.features.account.R

@Composable
fun AuthenticationUi(modifier: Modifier = Modifier, onAuthenticationRequest: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.landing_label_question),
            fontSize = MaterialTheme.typography.button.fontSize,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.alignByBaseline()
        )
        TextButton(onClick = onAuthenticationRequest, modifier = Modifier.alignByBaseline()) {
            Text(stringResource(id = R.string.landing_btn_auth), fontSize = 16.sp)
        }
    }
}