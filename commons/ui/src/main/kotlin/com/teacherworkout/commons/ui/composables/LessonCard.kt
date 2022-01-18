package com.teacherworkout.commons.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import com.teacherworkout.commons.ui.R
import com.teacherworkout.core.fragment.LessonStatus

@Composable
fun LessonCard(
    modifier: Modifier = Modifier,
    lessonStatus: LessonStatus,
    onClick: () -> Unit = {}
) {
    val lesson = remember { lessonStatus.lesson }

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
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
                painter = rememberImagePainter(lesson.thumbnail.url) {
                    error(R.drawable.art1)
                },
                contentDescription = lesson.title
            )

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
                        text = lesson.theme.title,
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
                        text = lesson.duration.displayValue,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    LinearProgressIndicator(
                        progress = lessonStatus.percentCompleted / 100f,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
