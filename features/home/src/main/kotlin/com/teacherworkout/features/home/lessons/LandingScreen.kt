package com.teacherworkout.features.home.lessons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.teacherworkout.commons.ui.composables.lessonThemesItem
import com.teacherworkout.commons.ui.composables.SearchView
import com.teacherworkout.commons.ui.composables.lessonsItem
import com.teacherworkout.features.home.R
import com.teacherworkout.features.home.lessons.CompanionObject.LessonInProgressItems
import com.teacherworkout.features.home.lessons.CompanionObject.NewLessonsItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

private val listState = LazyListState()

@Composable
fun LandingScreen(
    state: HomeContract.State,
    onSendEvent: (HomeContract.Event) -> Unit,
    effects: Flow<HomeContract.Effect>,
    onNavigationRequest: (HomeContract.Effect.Navigation) -> Unit
) {
    val space8dp = dimensionResource(id = R.dimen.space_8dp)
    val space16dp = dimensionResource(id = R.dimen.space_16dp)

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        effects.onEach { effect ->
            when (effect) {
                is HomeContract.Effect.Navigation.ToLessonDetails -> {
                    onNavigationRequest(effect)
                    snackbarHostState.showSnackbar(
                        message = "This should navigate to lesson with id ${effect.lessonId}",
                        duration = SnackbarDuration.Short,
                    )
                }
                is HomeContract.Effect.Navigation.ToLessonThemeDetails -> {
                    onNavigationRequest(effect)
                    snackbarHostState.showSnackbar(
                        message = "This should navigate to lesson theme with id ${effect.lessonThemeId}",
                        duration = SnackbarDuration.Short,
                    )
                }
            }
        }.collect()
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
                        onSendEvent(HomeContract.Event.SetSearchInput(it))
                    },
                    onClear = {
                        onSendEvent(HomeContract.Event.SetSearchInput(TextFieldValue("")))
                    },
                    placeholderText = stringResource(id = R.string.search_subject)
                )
                Spacer(modifier = Modifier.height(space16dp))
            }

            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight(0.5f),
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
                        onSendEvent(HomeContract.Event.SelectLesson(lesson.id))
                    }
                )
                item {
                    Spacer(modifier = Modifier.height(space16dp))
                }

                lessonsItem(
                    listTitle = R.string.new_lessons,
                    lessons = state.lessons.take(NewLessonsItems),
                    onLessonClick = { lesson ->
                        onSendEvent(HomeContract.Event.SelectLesson(lesson.id))
                    }
                )
                item {
                    Spacer(modifier = Modifier.height(space16dp))
                }

                lessonThemesItem(
                    lessonThemes = state.lessonThemes,
                    onLessonThemeClick = { lessonTheme ->
                        onSendEvent(HomeContract.Event.SelectLessonTheme(lessonTheme.id))
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

@Preview
@Composable
fun OnLandingScreenPreview() {
    LandingScreen(
        state = HomeContract.State(
            searchInput = TextFieldValue(),
            lessonThemes = emptyList(),
            isLoading = true
        ),
        onSendEvent = {},
        effects = flow {},
        onNavigationRequest = {}
    )
}

object CompanionObject {
    const val LessonInProgressItems = 2
    const val NewLessonsItems = 3
}
