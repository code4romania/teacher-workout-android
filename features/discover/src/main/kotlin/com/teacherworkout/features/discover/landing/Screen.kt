package com.teacherworkout.features.discover.landing

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
import com.teacherworkout.commons.ui.model.LessonTheme
import com.teacherworkout.features.discover.R
import com.teacherworkout.features.discover.landing.Companion.PROGRESS_HEIGHT_FRACTION
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private val listState = LazyListState()

@Composable
fun Screen(
    state: Contract.State,
    onSendEvent: (Contract.Event) -> Unit,
    effects: Flow<Contract.Effect>,
    onNavigationRequest: (Contract.Effect.Navigation) -> Unit
) {
    val space16dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_16dp)
    val snackbarHostState = remember { SnackbarHostState() }

    HandleEffects(effects) { effect ->
        when (effect) {
            is Contract.Effect.Navigation.ToLessonThemeDetails -> {
                onNavigationRequest(effect)
                snackbarHostState.showSnackbar(
                    message = "Will navigate to lesson theme with id ${effect.lessonThemeId}",
                    duration = SnackbarDuration.Short,
                )
            }
        }
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
                Header(state, onSendEvent)
            }
            if (state.isLoading) {
                item {
                    Loading(Modifier.fillParentMaxHeight(PROGRESS_HEIGHT_FRACTION))
                }
            } else {
                lessonThemesItem(
                    lessonThemes = state.themes.toViewModels(),
                    onLessonThemeClick = { lessonTheme ->
                        onSendEvent(Contract.Event.SelectLessonTheme(lessonTheme.id))
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

@Composable
fun Header(state: Contract.State, onSendEvent: (Contract.Event) -> Unit) {
    val space8dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_8dp)
    val space16dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_16dp)

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
            onSendEvent(Contract.Event.SetSearchInput(it))
        },
        onClear = {
            onSendEvent(Contract.Event.SetSearchInput(TextFieldValue("")))
        },
        placeholderText = stringResource(id = R.string.search_label)
    )
    Spacer(modifier = Modifier.height(space16dp))
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun DiscoverScreenPreview() {
    Screen(
        state = Contract.State(
            searchInput = TextFieldValue(),
            themes = listOf(Theme(id = "1", "Some theme"), Theme("2", "Other theme")),
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
    Screen(
        state = Contract.State(
            searchInput = TextFieldValue(),
            themes = emptyList(),
            isLoading = true
        ),
        onSendEvent = {},
        effects = flow {},
        onNavigationRequest = {}
    )
}

private fun List<Theme>.toViewModels(): List<LessonTheme> =
    this.map {
        LessonTheme(
            id = it.id,
            title = it.title,
            imageResourceId = com.teacherworkout.commons.ui.R.drawable.art2
        )
    }

private object Companion {
    const val PROGRESS_HEIGHT_FRACTION = 0.5f
}
