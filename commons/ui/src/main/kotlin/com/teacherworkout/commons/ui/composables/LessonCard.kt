package com.teacherworkout.commons.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.teacherworkout.commons.ui.R
import com.teacherworkout.commons.ui.model.Lesson

@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
    lesson: Lesson,
    onClick: () -> Unit = {}
) {
    val corner8dp = dimensionResource(id = R.dimen.corner_8dp)
    val border1dp = dimensionResource(id = R.dimen.border_1dp)

    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(corner8dp),
        border = BorderStroke(border1dp, MaterialTheme.colors.primary)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            LessonCardImage(
                imageResourceId = lesson.imageResourceId,
                contentDescription = lesson.title
            )
            LessonCardBody(lesson = lesson)
        }
    }
}

@Composable
private fun LessonCardImage(imageResourceId: Int, contentDescription: String) {
    Image(
        modifier = Modifier.fillMaxHeight(),
        contentScale = ContentScale.FillHeight,
        painter = painterResource(id = imageResourceId),
        contentDescription = contentDescription
    )
}

@Composable
private fun LessonCardBody(lesson: Lesson) {
    val space8dp = dimensionResource(id = R.dimen.space_8dp)
    val space4dp = dimensionResource(id = R.dimen.space_4dp)

    Column(
        modifier = Modifier.padding(space8dp, space4dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
        {
            Text(
                text = lesson.title,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
            )

        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Text(
                text = lesson.lessonThemeTitle,
                style = TextStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Schedule,
                tint = MaterialTheme.colors.primaryVariant,
                contentDescription = ""
            )
            Text(
                text = lesson.durationInMinutes.toString().plus(
                    stringResource(id = R.string.min)
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            LinearProgressIndicator(
                progress = lesson.remainingMinutes.toFloat()
                    .div(lesson.durationInMinutes.toFloat()),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
