package com.teacherworkout.features.profile.settings

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
        //TODO: update content description of the 'add photo' icon
        iconContentDescription = "",
        mainText = stringResource(R.string.label_change_picture),
        actionText = stringResource(R.string.label_upload_picture),
        onAction = {
            onCloseRequest()
            imagePicker.launch()
        },
        primaryColor = MaterialTheme.colors.primary,
        secondaryColor = MaterialTheme.colors.secondary
    )
}
