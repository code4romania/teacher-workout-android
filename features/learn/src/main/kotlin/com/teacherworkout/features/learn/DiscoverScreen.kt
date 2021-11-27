package com.teacherworkout.features.learn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.teacherworkout.features.learn.data.HomeData.dummyLessonThemes
import com.teacherworkout.features.learn.data.LessonTheme
import kotlin.random.Random

val lessonThemes = mutableStateOf(dummyLessonThemes)
var searchInput = mutableStateOf(TextFieldValue())
val listState = LazyListState()

//TODO: This will be changed when we will integrate the app with the backend
fun applyFilter(searchInput: String) {
    if (searchInput.isBlank()) {
        lessonThemes.value = dummyLessonThemes
    } else {
        val randomNumberOfLessonThemes = Random.nextInt(1, dummyLessonThemes.size)
        val someLessonThemes = mutableListOf<LessonTheme>()
        repeat(randomNumberOfLessonThemes) {
            someLessonThemes.add(dummyLessonThemes[Random.nextInt(0, dummyLessonThemes.size)])
        }
        lessonThemes.value = someLessonThemes
    }
}

@Composable
fun DiscoverScreen() {
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
                searchInput = searchInput,
                onSearchInputChange = {
                    searchInput.value = it
                    applyFilter(it.text)
                },
                onClear = {
                    searchInput.value = TextFieldValue()
                    applyFilter("")
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
        items(lessonThemes.value.chunked(2)) { oneOrTwoThemes ->
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

@Preview
@Composable
fun OnDiscoverScreenPreview() {
    DiscoverScreen()
}
