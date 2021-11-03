package com.teacherworkout.features.account.composables

import android.view.View
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.teacherworkout.features.account.R

@Composable
fun PasswordField(
    value: String,
    @StringRes labelTextId: Int,
    modifier: Modifier = Modifier,
    hasError: Boolean = false,
    enabled: Boolean = true,
    @StringRes errorTextId: Int = View.NO_ID,
    onValueChanged: (String) -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            enabled = enabled,
            onValueChange = onValueChanged,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    tint = MaterialTheme.colors.primaryVariant,
                    contentDescription = stringResource(id = R.string.cd_password_lock_icon)
                )
            },
            trailingIcon = {
                PasswordToggle(isVisible = isPasswordVisible) {
                    isPasswordVisible = !isPasswordVisible
                }
            },
            isError = hasError,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(labelTextId)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (hasError) {
            ErrorText(errorTextId = errorTextId)
        }
    }
}

@Composable
fun PasswordToggle(isVisible: Boolean, onVisibilityToggle: () -> Unit) {
    IconButton(onClick = onVisibilityToggle) {
        Icon(
            if (isVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
            tint = MaterialTheme.colors.primaryVariant,
            contentDescription = stringResource(id = R.string.cd_password_toggle_icon)
        )
    }
}
