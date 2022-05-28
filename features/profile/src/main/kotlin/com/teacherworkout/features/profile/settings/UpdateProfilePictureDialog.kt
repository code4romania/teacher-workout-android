package com.teacherworkout.features.profile.settings

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.teacherworkout.features.profile.R

@Composable
fun UpdateProfilePictureDialog(
    onDismiss: () -> Unit,
    onNewPicture: (Uri) -> Unit
) {
    val imagePicker = rememberImagePicker {
        onNewPicture(it)
    }
    GenericDialog(
        iconImageVector = Icons.Outlined.AddPhotoAlternate,
        iconContentDescription = stringResource(R.string.question_change_description),
        title = stringResource(R.string.question_change_picture),
        actionText = stringResource(R.string.label_upload_picture)
    ) {
        if (it == DialogResult.ACTION) {
            imagePicker.launch()
        }
        else {
            onDismiss()
        }
    }
}
