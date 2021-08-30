package com.teacherworkout.features.account.landing

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.teacherworkout.features.account.R

@Composable
fun RegistrationButton(modifier: Modifier = Modifier, onRegistrationRequest: () -> Unit) {
    val space8dp = dimensionResource(id =  R.dimen.space_8dp)
    val space32dp = dimensionResource(id =  R.dimen.space_32dp)
    Button(
        onClick = onRegistrationRequest, modifier = modifier, shape = RoundedCornerShape(space32dp)
    ) {
        Text(
            stringResource(id = R.string.landing_btn_register),
            modifier = Modifier.padding(vertical = space8dp),
            fontSize = MaterialTheme.typography.button.fontSize,
            fontWeight = FontWeight.ExtraBold
        )
    }
}