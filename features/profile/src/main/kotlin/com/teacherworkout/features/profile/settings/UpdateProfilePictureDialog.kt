package com.teacherworkout.features.profile.settings

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.teacherworkout.features.profile.R

@Composable
fun UpdateProfilePictureDialog(
    onNewPicture: (Uri) -> Unit,
    onDismiss: () -> Unit
) {
    val imagePicker = rememberImagePicker { onNewPicture(it) }

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
                    Icons.Outlined.AddPhotoAlternate,
                    MaterialTheme.colors.primary,
                    stringResource(R.string.question_change_description),
                    stringResource(R.string.question_change_picture)
                )
                DialogButton(
                    onClick = imagePicker::launch,
                    text = stringResource(R.string.label_upload_picture),
                    textColor = MaterialTheme.colors.onPrimary,
                    backgroundColor = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(space8dp))
                DialogButton(
                    onClick = onDismiss,
                    text = stringResource(R.string.cancel),
                    textColor = MaterialTheme.colors.error,
                    backgroundColor = MaterialTheme.colors.background
                )
                Spacer(modifier = Modifier.height(space8dp))
            }
        }
    }
}
