package com.example.lesson

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teacherworkout.commons.ui.model.Lesson
import com.teacherworkout.features.lesson.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun LessonStartScreen(
    state: LessonContract.State,
    onSendEvent: (LessonContract.Event) -> Unit,
    effects: Flow<LessonContract.Effect>
) {
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val space24dp = dimensionResource(id = R.dimen.space_24dp)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.landing_background))
            .padding(horizontal = space16dp, vertical = space24dp)
    ) {
        val (panel, image) = createRefs()

        val painter = painterResource(R.drawable.rocket_boy)
        Image(
            modifier = Modifier
                .aspectRatio(
                    ratio = painter.intrinsicSize.width / painter.intrinsicSize.height
                )
                .padding(space16dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(panel.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                },
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        Column(Modifier.constrainAs(panel) {
            bottom.linkTo(parent.bottom )
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.wrapContent
        }) {
            LessonTitleText(title = state.lesson.title)
            Spacer(modifier = Modifier.height(space24dp))
            LessonThemeTitleText(title = state.lesson.lessonThemeTitle)
            Spacer(modifier = Modifier.height(space16dp))
            DurationText(duration = state.lesson.durationInMinutes.toString() + " min")
            Spacer(modifier = Modifier.height(space24dp))
            StartContinueButton(
                lessonStarted = state.started,
                onClick = { onSendEvent(LessonContract.Event.StartContinue()) }
            )
            Spacer(modifier = Modifier.height(space24dp))
            if (state.saved) {
                UnsaveButton(onClick = { onSendEvent(LessonContract.Event.Unsave()) })
            } else {
                SaveButton(onClick = { onSendEvent(LessonContract.Event.Save()) })
            }
        }
    }
}

@Composable
private fun StartContinueButton(
    lessonStarted: Boolean,
    onClick: () -> Unit
) {
    val minTouchSize = dimensionResource(id = R.dimen.min_touch_size)

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(minTouchSize),
        onClick = onClick,
        shape = RoundedCornerShape(50)
    ) {
        Text(stringResource(if (lessonStarted) R.string.start_lesson_label else R.string.continue_lesson_label))
    }
}

@Composable
private fun LessonTitleText(title: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = title,
        textAlign = TextAlign.Center,
        style = TextStyle(
            color = MaterialTheme.colors.primary,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
private fun LessonThemeTitleText(title: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = title,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
    )
}

@Composable
private fun DurationText(duration: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        //TODO: pick the right icon
        //TODO: set the content description
        Icon(
            imageVector = Icons.Default.Timer,
            contentDescription = "",
            tint = MaterialTheme.colors.primary
        )
        Text(
            text = duration,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun SaveButton(onClick: () -> Unit) {
    val minTouchSize = dimensionResource(id = R.dimen.min_touch_size)

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(minTouchSize),
        onClick = onClick,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(R.color.landing_background),
            //TODO: pick a color that is not hard coded
            contentColor = Color.Black
        )
    ) {
        Text(stringResource(R.string.save_lesson_label))
    }
}

@Composable
private fun UnsaveButton(onClick: () -> Unit) {
    val minTouchSize = dimensionResource(id = R.dimen.min_touch_size)

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(minTouchSize),
        onClick = onClick,
        shape = RoundedCornerShape(50)
    ) {
        //TODO: decide on a display name for the `Unsave` Button
        Text("TODO")
    }
}

@Preview
@Composable
fun LessonStartScreenPreview() {
    LessonStartScreen(
        LessonContract.State(
            lesson = Lesson(
                id = 1,
                title = "Cum discuti cu elevii tai despre boli psihice",
                lessonThemeTitle = "Cum discuti despre boli psihice",
                imageResourceId = R.drawable.art1,
                durationInMinutes = 7,
                remainingMinutes = 4
            ),
            started = false,
            saved = false
        ),
        {},
        flow {}
    )
}
