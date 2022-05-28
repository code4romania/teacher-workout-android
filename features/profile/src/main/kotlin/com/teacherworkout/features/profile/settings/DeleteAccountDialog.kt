package com.teacherworkout.features.profile.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.teacherworkout.features.profile.R

@Composable
fun DeleteAccountDialog(onAccountDelete: () -> Unit, onDismiss: () -> Unit) {
    val space8dp = dimensionResource(id = R.dimen.space_8dp)
    val space24dp = dimensionResource(id = R.dimen.space_24dp)
    val corner8dp = dimensionResource(id = R.dimen.corner_8dp)

    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = RoundedCornerShape(corner8dp)) {
            Column(
                modifier = Modifier.padding(
                    start = space24dp,
                    top = space24dp,
                    end = space24dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DialogBody(
                    Icons.Outlined.Warning,
                    MaterialTheme.colors.error,
                    stringResource(R.string.question_delete_description),
                    stringResource(R.string.question_delete_account),
                    stringResource(R.string.warning_delete_account),
                )

                DialogButton(
                    onClick = onAccountDelete,
                    text = stringResource(R.string.label_delete_account),
                    textColor = MaterialTheme.colors.onPrimary,
                    backgroundColor = MaterialTheme.colors.error
                )
                Spacer(modifier = Modifier.height(space8dp))

                DialogButton(
                    onClick = onDismiss,
                    text = stringResource(android.R.string.cancel),
                    textColor = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.background
                )
                Spacer(modifier = Modifier.height(space8dp))
            }
        }
    }
}
