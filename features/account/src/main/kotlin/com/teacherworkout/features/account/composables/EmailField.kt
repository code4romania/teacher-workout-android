package com.teacherworkout.features.account.composables

import android.view.View
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.teacherworkout.features.account.R

@Composable
fun EmailField(
    value: String,
    @StringRes labelTextId: Int,
    modifier: Modifier = Modifier,
    @StringRes errorTextId: Int = View.NO_ID,
    hasError: Boolean = false,
    enabled: Boolean = true,
    onValueChanged: (String) -> Unit,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            enabled = enabled,
            isError = hasError,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    tint = MaterialTheme.colors.primaryVariant,
                    contentDescription = stringResource(id = R.string.cd_email_icon)
                )
            },
            label = { Text(text = stringResource(labelTextId)) },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValueChanged,
        )
        if (hasError) {
            ErrorText(errorTextId = errorTextId)
        }
    }
}
