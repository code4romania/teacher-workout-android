package com.teacherworkout.features.profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.teacherworkout.features.profile.R

@Composable
fun GenericDialog(
    isOpen: Boolean,
    onCloseRequest: () -> Unit,
    onDismissRequest: () -> Unit,
    iconImageVector: ImageVector,
    iconContentDescription: String,
    mainText: String,
    secondaryText: String? = null,
    actionText: String,
    onAction: () -> Unit,
    primaryColor: Color,
    secondaryColor: Color
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    if(isOpen) {
        Dialog(onDismissRequest = onDismissRequest) {
            val space16dp = dimensionResource(id = R.dimen.space_16dp)
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
                            .background(primaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = iconImageVector,
                            tint = MaterialTheme.colors.onPrimary,
                            contentDescription = iconContentDescription
                        )
                    }
                    Spacer(modifier = Modifier.height(space24dp))
                    Text(
                        modifier = Modifier.widthIn(max = screenWidth / 2),
                        text = mainText,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    if (secondaryText != null) {
                        Spacer(modifier = Modifier.height(space16dp))
                        Text(
                            text = secondaryText,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.body1.fontSize,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(space24dp))
                    Button(
                        modifier = Modifier
                            .height(minTouchSize)
                            .fillMaxWidth(),
                        onClick = onAction,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = primaryColor
                        )
                    ) {
                        Text(
                            text = actionText,
                            color = MaterialTheme.colors.onPrimary
                        )
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
                            color = secondaryColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(defaultPressedButtonElevation))
                }
            }
        }
    }
}