package com.teacherworkout.features.home.data

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.teacherworkout.features.learn.R

@Composable
fun LessonInProgressCard(
    modifier: Modifier = Modifier,
    lesson: LessonsInProgress,
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
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = lesson.imageResourceId),
                contentDescription = lesson.title
            )
            Row(
                modifier = Modifier
                    .padding(space8dp, space4dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = lesson.title,
                )
            }
        }
    }
}
