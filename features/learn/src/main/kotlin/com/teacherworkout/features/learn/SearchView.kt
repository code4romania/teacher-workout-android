package com.teacherworkout.features.learn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    searchInput: TextFieldValue,
    onSearchInputChange: (TextFieldValue) -> Unit,
    onClear: () -> Unit,
    placeholderText: String
) {
    val corner8dp = dimensionResource(id = R.dimen.corner_8dp)
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier,
        shape = RoundedCornerShape(corner8dp),
        value = searchInput,
        onValueChange = onSearchInputChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        placeholder = {
            Text(placeholderText)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                //TODO: add a string resource for the Search icon's content description
                contentDescription = ""
            )
        },
        trailingIcon = {
            if (searchInput.text.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onClear() },
                    imageVector = Icons.Default.Close,
                    //TODO: add a string resource for the Close icon's content description
                    contentDescription = "",
                )
            }
        }
    )
}
