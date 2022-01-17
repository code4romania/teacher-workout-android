package com.teacherworkout.features.lesson

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.teacherworkout.features.account.composables.UpIcon
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect

@Composable
fun LessonStartScreen(
    state: LessonContract.State,
    onSendEvent: (LessonContract.Event) -> Unit,
    effects: Flow<LessonContract.Effect>,
    onNavigationRequest: (LessonContract.Effect.Navigation) -> Unit
) {
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val space24dp = dimensionResource(id = R.dimen.space_24dp)

     LaunchedEffect(Unit) {
        effects.onEach { effect ->
            when (effect) {
                is LessonContract.Effect.Navigation.NavigateUp -> {
                    onNavigationRequest(effect)
                }
            }
        }.collect()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(R.color.landing_background),
                elevation = 0.dp
            ) {
                UpIcon(tint = MaterialTheme.colors.primary) {
                    onSendEvent(LessonContract.Event.NavigateUp)
                }
            }
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.landing_background))
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
                bottom.linkTo(parent.bottom )
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.wrapContent
            }) {
                state.lesson?.let { lesson->
                    LessonTitleText(title = lesson.title)
                    Spacer(modifier = Modifier.height(space24dp))
                    LessonThemeTitleText(title = lesson.theme.title)
                    Spacer(modifier = Modifier.height(space16dp))
                    //TODO: determine which lesson attribute to use for the duration text
                    DurationText(duration = lesson.duration.displayValue)
                    Spacer(modifier = Modifier.height(space24dp))
                    StartContinueButton(
                        lessonStarted = true,
                        onClick = { onSendEvent(LessonContract.Event.StartContinue) }
                    )
                    Spacer(modifier = Modifier.height(space24dp))
                    if (true) {
                        UnsaveButton(onClick = { onSendEvent(LessonContract.Event.Unsave) })
                    } else {
                        SaveButton(onClick = { onSendEvent(LessonContract.Event.Save) })
                    }
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
        //TODO: set the content description to the duration icon
        Icon(
            imageVector = Icons.Default.Schedule,
            contentDescription = "",
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
