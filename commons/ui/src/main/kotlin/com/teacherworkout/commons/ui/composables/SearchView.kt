package com.teacherworkout.commons.ui.composables

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.teacherworkout.commons.ui.R

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
                contentDescription = stringResource(id = R.string.search_content_description)
            )
        },
        trailingIcon = {
            if (searchInput.text.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onClear() },
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_content_description),
                )
            }
        }
    )
}
