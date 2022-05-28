package com.teacherworkout.features.profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teacherworkout.features.profile.R

@Composable
fun DialogBody(
    iconImageVector: ImageVector,
    iconTint: Color,
    iconContentDescription: String,
    mainText: String,
    secondaryText: String? = null,
) {
    val minTouchSize = dimensionResource(id = R.dimen.min_touch_size)
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val space24dp = dimensionResource(id = R.dimen.space_24dp)

    Box(
        modifier = Modifier
            .size(minTouchSize)
            .clip(CircleShape)
            .background(iconTint),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = iconImageVector,
            tint = Color.White,
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
        ),
        color = Color.Black
    )
    if (secondaryText != null) {
        Spacer(modifier = Modifier.height(space16dp))
        Text(
            text = secondaryText,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = MaterialTheme.typography.body1.fontSize,
                fontWeight = FontWeight.Normal
            ),
            color = Color.Black
        )
    }
    Spacer(modifier = Modifier.height(space24dp))
}

@Composable
fun DialogButton(
    onClick: () -> Unit,
    text: String,
    textColor: Color,
    backgroundColor: Color
)
{
    val minTouchSize = dimensionResource(id = R.dimen.min_touch_size)

    Button(
        modifier = Modifier.height(minTouchSize),
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp)
    ) {
        Text(
            text = text.uppercase(),
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}
