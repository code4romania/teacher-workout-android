package com.teacherworkout.features.learn.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
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
import com.teacherworkout.features.learn.LessonThemeCard
import com.teacherworkout.features.learn.R
import com.teacherworkout.features.learn.SearchView
import com.teacherworkout.features.learn.data.impl.FakeLessonsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

private val listState = LazyListState()

@Composable
fun DiscoverScreen(
    state: DiscoverContract.State,
    onSendEvent: (DiscoverContract.Event) -> Unit,
    effects: Flow<DiscoverContract.Effect>,
    onNavigationRequest: (DiscoverContract.Effect.Navigation) -> Unit
) {
    val space32dp = dimensionResource(id = R.dimen.space_32dp)
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val space8dp = dimensionResource(id = R.dimen.space_8dp)

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        effects.onEach { effect ->
            when(effect) {
                is DiscoverContract.Effect.Navigation.ToLessonThemeDetails -> {
                    onNavigationRequest(effect)
                    //TODO: remove the Snackbar related code when the navigation is fully implemented
                    snackbarHostState.showSnackbar(
                        message = "Will navigate to \"${effect.lessonThemeName}\"",
                        duration = SnackbarDuration.Short,
                    )
                }
            }
        }.collect()
    }

    Box {
        //TODO: consider switching to LazyVerticalGrid if it becomes stable
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
                    text = stringResource(id = R.string.discover_lessons_title),
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
                        onSendEvent(DiscoverContract.Event.SetSearchInput(it))
                    },
                    onClear = {
                        onSendEvent(DiscoverContract.Event.SetSearchInput(TextFieldValue("")))
                    },
                    placeholderText = stringResource(id = R.string.search_label)
                )
                Spacer(modifier = Modifier.height(space32dp))
                Text(
                    text = stringResource(id = R.string.themes_title),
                    style = TextStyle(
                        color = MaterialTheme.colors.primary,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            if(state.isLoading) {
                item {
                    Box(
                        //TODO: look for a better way to center the progress bar
                        modifier = Modifier.fillMaxWidth().fillParentMaxHeight(0.5f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                items(state.lessonThemes.chunked(2)) { oneOrTwoThemes ->
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        @Composable
                        fun LessonThemeCardUtil(themeIndex: Int) {
                            val heightLessonThemeCard = dimensionResource(id = R.dimen.lesson_theme_card_height)
                            LessonThemeCard(
                                modifier = Modifier
                                    .height(heightLessonThemeCard)
                                    .weight(1f),
                                lessonTheme = oneOrTwoThemes[themeIndex],
                                onClick = {
                                    onSendEvent(
                                        DiscoverContract.Event.SelectLessonTheme(
                                            oneOrTwoThemes[themeIndex].title
                                        )
                                    )
                                }
                            )
                        }
                        LessonThemeCardUtil(themeIndex = 0)
                        Spacer(modifier = Modifier.width(space16dp))
                        if (oneOrTwoThemes.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        } else {
                            LessonThemeCardUtil(themeIndex = 1)
                        }
                    }
                }
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
fun DiscoverScreenPreview() {
    DiscoverScreen(
        state = DiscoverContract.State(
            searchInput = TextFieldValue(),
            lessonThemes = FakeLessonsRepository().dummyLessonThemes,
            isLoading = false
        ),
        onSendEvent = {},
        effects = flow {},
        onNavigationRequest = {}
    )
}

@Preview
@Composable
fun DiscoverScreenLoadingPreview() {
    DiscoverScreen(
        state = DiscoverContract.State(
            searchInput = TextFieldValue(),
            lessonThemes = emptyList(),
            isLoading = true
        ),
        onSendEvent = {},
        effects = flow {},
        onNavigationRequest = {}
    )
}
