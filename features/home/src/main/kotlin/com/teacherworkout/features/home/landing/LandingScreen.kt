package com.teacherworkout.features.home.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.teacherworkout.commons.ui.composables.HandleEffects
import com.teacherworkout.commons.ui.composables.SearchView
import com.teacherworkout.commons.ui.composables.lessonThemesItem
import com.teacherworkout.commons.ui.composables.lessonsItem
import com.teacherworkout.commons.ui.theming.TeacherWorkoutTheme
import com.teacherworkout.features.home.R
import com.teacherworkout.features.home.landing.CompanionObject.LessonInProgressItems
import com.teacherworkout.features.home.landing.CompanionObject.NewLessonsItems
import com.teacherworkout.features.home.landing.CompanionObject.ProgressBarHeightFraction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private val listState = LazyListState()

@SuppressWarnings("LongMethod")
@Composable
fun LandingScreen(
    state: LandingContract.State,
    sendEvent: (LandingContract.Event) -> Unit,
    effects: Flow<LandingContract.Effect>,
    onNavigationRequest: (LandingContract.Effect.Navigation) -> Unit
) {
    val space16dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_16dp)

    val snackbarHostState = remember { SnackbarHostState() }

    HandleEffects(effects) {
        handleEffect(it, onNavigationRequest, snackbarHostState)
    }

    Box {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = space16dp),
            verticalArrangement = Arrangement.spacedBy(space16dp),
            state = listState
        ) {
            item {
                Header(state, sendEvent)
            }

            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(ProgressBarHeightFraction),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                lessonsItem(
                    listTitle = R.string.lessons_in_progress,
                    lessons = state.lessons.take(LessonInProgressItems),
                    onLessonClick = { lesson ->
                        sendEvent(LandingContract.Event.SelectLesson(lesson.id))
                    }
                )
                item {
                    Spacer(modifier = Modifier.height(space16dp))
                }
                lessonsItem(
                    listTitle = R.string.new_lessons,
                    lessons = state.lessons.take(NewLessonsItems),
                    onLessonClick = { lesson ->
                        sendEvent(LandingContract.Event.SelectLesson(lesson.id))
                    }
                )
                item {
                    Spacer(modifier = Modifier.height(space16dp))
                }
                lessonThemesItem(
                    lessonThemes = state.lessonThemes,
                    onLessonThemeClick = { lessonTheme ->
                        sendEvent(LandingContract.Event.SelectLessonTheme(lessonTheme.id))
                    }
                )
                item {
                    Spacer(modifier = Modifier.height(space16dp))
                }
            }
        }
        SnackbarHost(
            modifier = Modifier.align(Alignment.BottomCenter),
            hostState = snackbarHostState
        )
    }
}

private suspend fun handleEffect(
    effect: LandingContract.Effect,
    onNavigationRequest: (LandingContract.Effect.Navigation) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    when (effect) {
        is LandingContract.Effect.Navigation.ToLessonDetails -> {
            onNavigationRequest(effect)
            snackbarHostState.showSnackbar(
                message = "This should navigate to lesson with id ${effect.lessonId}",
                duration = SnackbarDuration.Short,
            )
        }
        is LandingContract.Effect.Navigation.ToLessonThemeDetails -> {
            onNavigationRequest(effect)
            snackbarHostState.showSnackbar(
                message = "This should navigate to lesson theme with id ${effect.lessonThemeId}",
                duration = SnackbarDuration.Short,
            )
        }
    }
}

@Composable
private fun Header(
    state: LandingContract.State,
    onSendEvent: (LandingContract.Event) -> Unit,
) {
    val space8dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_8dp)
    val space16dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_16dp)

    Spacer(modifier = Modifier.height(space16dp))
    Text(
        text = stringResource(id = R.string.search_lesson_title),
        style = TextStyle(
            color = MaterialTheme.colors.primary,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )
    )
    Spacer(modifier = Modifier.height(space8dp))
    SearchView(
        modifier = Modifier.fillMaxWidth(),
        searchInput = state.searchInput,
        onSearchInputChange = {
            onSendEvent(LandingContract.Event.SetSearchInput(it))
        },
        onClear = {
            onSendEvent(LandingContract.Event.SetSearchInput(TextFieldValue("")))
        },
        placeholderText = stringResource(id = R.string.search_subject)
    )
    Spacer(modifier = Modifier.height(space16dp))
}

@Preview
@Composable
fun OnLandingScreenPreview() {
    TeacherWorkoutTheme() {
        LandingScreen(
            state = LandingContract.State(
                searchInput = TextFieldValue(),
                lessonThemes = emptyList(),
                isLoading = true
            ),
            sendEvent = {},
            effects = flow {},
            onNavigationRequest = {}
        )
    }
}

object CompanionObject {
    const val LessonInProgressItems = 2
    const val NewLessonsItems = 3
    const val ProgressBarHeightFraction = 0.5f
}
