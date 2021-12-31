package com.teacherworkout.features.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    pictureUri: Uri? = null,
) {
    val profilePictureSize = dimensionResource(id = R.dimen.profile_photo_size)
    val defaultProfilePictureId = R.drawable.ic_profile_default
    Image(
        modifier = modifier.size(profilePictureSize),
        painter = rememberImagePainter(pictureUri ?: defaultProfilePictureId) {
            transformations(CircleCropTransformation())
        },
        contentDescription = stringResource(id = R.string.cd_profile_photo),
    )
}
