package com.teacherworkout.commons.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil.compose.rememberImagePainter
import com.teacherworkout.commons.ui.R
import com.teacherworkout.core.fragment.LessonTheme

@Composable
fun LessonThemeCard(
    modifier: Modifier = Modifier,
    lessonTheme: LessonTheme,
    onClick: () -> Unit = {}
) {
    val corner8dp = dimensionResource(id = R.dimen.corner_8dp)
    val space8dp = dimensionResource(id = R.dimen.space_8dp)
    val space4dp = dimensionResource(id = R.dimen.space_4dp)
    val border1dp = dimensionResource(id = R.dimen.border_1dp)
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(corner8dp),
        border = BorderStroke(border1dp, MaterialTheme.colors.primary)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
                painter = rememberImagePainter(lessonTheme.thumbnail.url) {
                    error(R.drawable.art1)
                },
                contentDescription = lessonTheme.thumbnail.description
            )
            Row(
                modifier = Modifier
                    .padding(space8dp, space4dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = lessonTheme.title,
                )
            }
        }
    }
}
