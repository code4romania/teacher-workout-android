package com.teacherworkout.features.profile.landing

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.teacherworkout.features.profile.R
import com.teacherworkout.commons.ui.R as CommonRes


@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    pictureUri: Uri? = null,
) {
    val profilePictureSize = dimensionResource(id = CommonRes.dimen.profile_photo_size)
    val defaultProfilePictureId = R.drawable.ic_profile_default
    Image(
        modifier = modifier.size(profilePictureSize).clip(CircleShape),
        painter = rememberAsyncImagePainter(pictureUri ?: defaultProfilePictureId),
        contentDescription = stringResource(id = R.string.cd_profile_photo),
    )
}
