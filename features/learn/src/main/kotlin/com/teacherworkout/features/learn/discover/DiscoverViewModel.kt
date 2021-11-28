package com.teacherworkout.features.learn.discover

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.features.learn.data.HomeData
import com.teacherworkout.features.learn.data.LessonTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class DiscoverViewModel: BaseViewModel<DiscoverContract.Event, DiscoverContract.State, DiscoverContract.Effect>() {
    override fun setInitialState(): DiscoverContract.State {
        return DiscoverContract.State()
    }

    override fun handleEvents(event: DiscoverContract.Event) {
        when(event) {
            is DiscoverContract.Event.SetSearchInput -> setSearchInput(event.searchInput)
            is DiscoverContract.Event.SelectLessonTheme -> selectLessonTheme(event.lessonName)
        }
    }

    init {
        refreshLessonThemes()
    }

    private fun refreshLessonThemes() {
        viewModelScope.launch {
            val lessonThemes = HomeData.dummyLessonThemes
            setState { copy(lessonThemes = lessonThemes) }
        }
    }

    private fun setSearchInput(searchInput: TextFieldValue) {
        val prevSearchInput = viewState.value.searchInput
        setState { copy(searchInput = searchInput) }
        if(prevSearchInput.text != searchInput.text) {
            applyFilter(searchInput.text)
        }
    }

    private fun selectLessonTheme(lessonThemeName: String) {
        setEffect {
            DiscoverContract.Effect.Navigation.ToLessonThemeDetails(lessonThemeName)
        }
    }

    //TODO: This will be changed when we will integrate the app with the backend
    private fun applyFilter(searchInput: String) {
        if (searchInput.isBlank()) {
             setState{ copy(lessonThemes = HomeData.dummyLessonThemes) }
        } else {
            val randomNumberOfLessonThemes = Random.nextInt(1, HomeData.dummyLessonThemes.size)
            val someLessonThemes = mutableListOf<LessonTheme>()
            repeat(randomNumberOfLessonThemes) {
                someLessonThemes.add(HomeData.dummyLessonThemes[Random.nextInt(0, HomeData.dummyLessonThemes.size)])
            }
            setState{ copy(lessonThemes = someLessonThemes) }
        }
    }
}