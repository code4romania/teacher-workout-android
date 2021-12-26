package com.teacherworkout.features.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
    val profileSize = dimensionResource(id = R.dimen.profile_photo_size)
    val defaultImageId = R.drawable.ic_profile_default
    Image(
        painter = rememberImagePainter(imageUri ?: defaultImageId) {
            transformations(CircleCropTransformation())
        },
        contentDescription = stringResource(id = R.string.cd_profile_photo),
        modifier = modifier
            .width(profileSize)
            .height(profileSize)
    )
}
