package com.teacherworkout.features.profile.results

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.teacherworkout.features.profile.R

@Composable
fun LessonRow(
    lessonCategoryText: String,
    completedLessons: Int,
    totalLessons: Int,
    onSelection: () -> Unit
) {
    val space4dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_4dp)
    val space8dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_8dp)
    val space16dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_16dp)
    val lessonPhotoSize = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.lesson_photo_size)
    Column(modifier = Modifier.clickable { onSelection() }) {
        Row(modifier = Modifier.padding(space16dp)) {
            Image(
                painter = painterResource(id = R.drawable.default_lesson_picture),
                contentDescription = stringResource(id = R.string.cd_lesson, lessonCategoryText),
                modifier = Modifier
                    .width(lessonPhotoSize)
                    .height(lessonPhotoSize)
                    .clip(RoundedCornerShape(space8dp))
            )
            Spacer(Modifier.width(space16dp))
            Column {
                Text(
                    text = lessonCategoryText,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(space4dp))
                Text(
                    text = stringResource(
                        id = R.string.lesson_progress,
                        completedLessons.toString(),
                        totalLessons.toString()
                    ),
                    fontSize = MaterialTheme.typography.body2.fontSize
                )
                Spacer(Modifier.height(space4dp))
                LessonProgress(progress = completedLessons.toFloat() / totalLessons.toFloat())
            }
        }
        Divider(modifier = Modifier.padding(start = space16dp, end = space16dp))
    }
}
