package com.teacherworkout.features.lesson

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.teacherworkout.commons.ui.composables.HandleEffects
import com.teacherworkout.commons.ui.model.Lesson
import com.teacherworkout.features.account.composables.UpIcon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.teacherworkout.commons.ui.R as CommonRes


@Composable
fun LessonStartScreen(
    state: LessonContract.State,
    onSendEvent: (LessonContract.Event) -> Unit,
    effects: Flow<LessonContract.Effect>,
    onNavigationRequest: (LessonContract.Effect.Navigation) -> Unit
) {
    HandleEffects(effects) { effect ->
        when (effect) {
            is LessonContract.Effect.Navigation.NavigateUp -> onNavigationRequest(effect)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(CommonRes.color.landing_background),
                elevation = 0.dp
            ) {
                UpIcon(tint = MaterialTheme.colors.primary) {
                    onSendEvent(LessonContract.Event.NavigateUp)
                }
            }
        }
    ) { paddingValues ->
        LessonScreenBody(Modifier.padding(paddingValues), state, onSendEvent)
    }
}

@Composable
private fun LessonScreenBody(
    modifier: Modifier = Modifier,
    state: LessonContract.State,
    onSendEvent: (LessonContract.Event) -> Unit
) {
    val space16dp = dimensionResource(id = CommonRes.dimen.space_16dp)
    val space24dp = dimensionResource(id = CommonRes.dimen.space_24dp)

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(CommonRes.color.landing_background))
            .padding(start = space16dp, end = space16dp, bottom = space24dp)
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
            bottom.linkTo(parent.bottom)
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.wrapContent
        }) {
            state.lesson?.let { lesson ->
                val minutesString = stringResource(CommonRes.string.min)
                LessonTitleText(title = lesson.title)
                Spacer(modifier = Modifier.height(space24dp))
                LessonThemeTitleText(title = lesson.lessonThemeTitle)
                Spacer(modifier = Modifier.height(space16dp))
                DurationText(duration = "${lesson.remainingMinutes} $minutesString")
                Spacer(modifier = Modifier.height(space24dp))
                StartContinueButton(
                    lessonStarted = lesson.started,
                    onClick = { onSendEvent(LessonContract.Event.StartContinue) }
                )
                Spacer(modifier = Modifier.height(space24dp))
                if (lesson.saved) {
                    DeleteButton(onClick = { onSendEvent(LessonContract.Event.Unsave) })
                } else {
                    SaveButton(onClick = { onSendEvent(LessonContract.Event.Save) })
                }
            }
        }
    }
}

@Composable
private fun StartContinueButton(
    lessonStarted: Boolean,
    onClick: () -> Unit
) {
    val minTouchSize = dimensionResource(id = CommonRes.dimen.min_touch_size)

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(minTouchSize),
        onClick = onClick,
        shape = MaterialTheme.shapes.large
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
        Icon(
            imageVector = Icons.Default.Schedule,
            contentDescription = stringResource(id = R.string.schedule_content_description),
            tint = MaterialTheme.colors.primary
        )
        Text(
            text = " ".plus(duration),
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun SaveButton(onClick: () -> Unit) {
    val minTouchSize = dimensionResource(id = CommonRes.dimen.min_touch_size)

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(minTouchSize),
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(CommonRes.color.landing_background),
            contentColor = MaterialTheme.colors.onBackground
        )
    ) {
        Text(stringResource(R.string.save_lesson_label))
    }
}

@Composable
private fun DeleteButton(onClick: () -> Unit) {
    val minTouchSize = dimensionResource(id = CommonRes.dimen.min_touch_size)

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(minTouchSize),
        onClick = onClick,
        shape = MaterialTheme.shapes.large
    ) {
        Text(stringResource(id = R.string.delete_lesson_label))
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
                imageResourceId = CommonRes.drawable.art1,
                durationInMinutes = 7,
                remainingMinutes = 4
            )
        ),
        {},
        flow {},
        {}
    )
}
