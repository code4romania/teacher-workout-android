package com.teacherworkout.features.profile.settings

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.teacherworkout.features.profile.R

@Composable
fun UpdateProfilePictureDialog(
    isOpen: Boolean,
    onCloseRequest: () -> Unit,
    onDismissRequest: () -> Unit,
    onNewPicture: (Uri) -> Unit
) {
    val imagePicker = rememberImagePicker {
        onNewPicture(it)
    }
    GenericDialog(
        isOpen = isOpen,
        onCloseRequest = onCloseRequest,
        onDismissRequest = onDismissRequest,
        iconImageVector = Icons.Outlined.AddPhotoAlternate,
        iconContentDescription = stringResource(R.string.question_change_description),
        mainText = stringResource(R.string.question_change_picture),
        actionText = stringResource(R.string.label_upload_picture),
        onAction = {
            onCloseRequest()
            imagePicker.launch()
        },
        primaryColor = MaterialTheme.colors.primary,
        secondaryColor = MaterialTheme.colors.secondary
    )
}
