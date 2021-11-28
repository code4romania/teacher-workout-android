package com.teacherworkout.features.learn.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.teacherworkout.features.learn.LessonThemeCard
import com.teacherworkout.features.learn.R
import com.teacherworkout.features.learn.SearchView

private val listState = LazyListState()

@Composable
fun DiscoverScreen(
    state: DiscoverContract.State,
    sendEvent: (DiscoverContract.Event) -> Unit,
    navController: NavController
) {
    val space32dp = dimensionResource(id = R.dimen.space_32dp)
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val space8dp = dimensionResource(id = R.dimen.space_8dp)

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
                    sendEvent(DiscoverContract.Event.SetSearchInput(it))
                },
                onClear = {
                    sendEvent(DiscoverContract.Event.SetSearchInput(TextFieldValue("")))
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
        items(state.lessonThemes.chunked(2)) { oneOrTwoThemes ->
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                val heightLessonThemeCard = dimensionResource(id = R.dimen.lesson_theme_card_height)
                LessonThemeCard(
                    modifier = Modifier
                        .height(heightLessonThemeCard)
                        .weight(1f),
                    lessonTheme = oneOrTwoThemes[0]
                )
                Spacer(modifier = Modifier.width(space16dp))
                if (oneOrTwoThemes.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                } else {
                    LessonThemeCard(
                        modifier = Modifier
                            .height(heightLessonThemeCard)
                            .weight(1f),
                        lessonTheme = oneOrTwoThemes[1]
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(space16dp))
        }
    }
}

/*@Preview
@Composable
fun OnDiscoverScreenPreview() {
    DiscoverScreen()
}*/
