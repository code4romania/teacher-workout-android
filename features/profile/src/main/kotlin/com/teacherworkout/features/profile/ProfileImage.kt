package com.teacherworkout.features.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun ProfileImage(modifier: Modifier = Modifier, @DrawableRes imageId: Int = R.drawable.ic_profile_default) {
    val fadedGray = colorResource(id = R.color.faded_gray)
    val profileSize = dimensionResource(id = R.dimen.profile_photo_size)
    Image(
        painter = painterResource(id = imageId),
        contentDescription = stringResource(id = R.string.cd_profile_photo),
        modifier = modifier
            .width(profileSize)
            .height(profileSize)
            .clip(RoundedCornerShape(profileSize / 2))
            .background(fadedGray)
    )
}
