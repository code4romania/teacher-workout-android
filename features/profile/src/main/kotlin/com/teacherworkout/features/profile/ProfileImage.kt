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
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUri: Uri? = null
) {
    val profilePhotoSize = dimensionResource(id = R.dimen.profile_photo_size)
    val defaultProfileImageId = R.drawable.ic_profile_default
    Image(
        modifier = modifier.size(profilePhotoSize),
        painter = rememberImagePainter(imageUri ?: defaultProfileImageId) {
            transformations(CircleCropTransformation())
        },
        contentDescription = stringResource(id = R.string.cd_profile_photo),
    )
}
