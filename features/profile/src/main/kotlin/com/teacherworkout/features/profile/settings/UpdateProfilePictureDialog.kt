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
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val imagePicker = rememberImagePicker {
        onNewPicture(it)
    }

    if(isOpen) {
        Dialog(onDismissRequest = onDismissRequest) {
            val space24dp = dimensionResource(id = R.dimen.space_24dp)
            val corner8dp = dimensionResource(id = R.dimen.corner_8dp)
            val minTouchSize = dimensionResource(id = R.dimen.min_touch_size)

            Surface(shape = RoundedCornerShape(corner8dp)) {
                Column(
                    modifier = Modifier.padding(
                        start = space24dp,
                        top  = space24dp,
                        end = space24dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(minTouchSize)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddPhotoAlternate,
                            tint = MaterialTheme.colors.onPrimary,
                            //TODO: update content description of the 'add photo' icon
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.height(space24dp))
                    Text(
                        modifier = Modifier.widthIn(max = screenWidth / 2),
                        text = stringResource(R.string.label_change_picture),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(space24dp))
                    Button(
                        modifier = Modifier
                            .height(minTouchSize)
                            .fillMaxWidth(),
                        onClick = {
                            onCloseRequest()
                            imagePicker.launch()
                        },
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(text = stringResource(R.string.label_upload_picture))
                    }
                    //TODO: find a not hard coded way to obtain the button's default pressed elevation
                    val defaultPressedButtonElevation = 8.dp
                    Spacer(modifier = Modifier.height(defaultPressedButtonElevation))
                    Button(
                        modifier = Modifier.height(minTouchSize),
                        onClick = onCloseRequest,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.surface
                        ),
                        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(defaultPressedButtonElevation))
                }
            }
        }
    }
}
