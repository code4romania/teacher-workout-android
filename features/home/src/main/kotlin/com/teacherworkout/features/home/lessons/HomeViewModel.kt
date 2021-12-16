package com.teacherworkout.features.home.lessons

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.commons.ui.data.LessonsRepository
import com.teacherworkout.commons.ui.data.Result
import com.teacherworkout.commons.ui.model.LessonTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(
    private val lessonsRepository: LessonsRepository
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    private var allLessonThemes: List<LessonTheme> = emptyList()

    override fun setInitialState(): HomeContract.State {
        return HomeContract.State()
    }

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.SetSearchInput -> setSearchInput(event.searchInput)
            is HomeContract.Event.SelectLessonTheme -> selectLessonTheme(event.lessonThemeName)
        }
    }

    init {
        refreshLessonThemes()
    }

    private fun refreshLessonThemes() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            val result = lessonsRepository.getAllLessonThemes()
            setState { copy(isLoading = false) }
            when (result) {
                is Result.Success -> {
                    allLessonThemes = result.data
                    setState {
                        copy(
                            searchInput = TextFieldValue(),
                            lessonThemes = allLessonThemes
                        )
                    }
                }
                is Result.Error -> {

                }
            }
        }
    }

    private fun setSearchInput(searchInput: TextFieldValue) {
        val prevSearchInput = viewState.value.searchInput
        setState { copy(searchInput = searchInput) }
        if (prevSearchInput.text != searchInput.text) {
            applyFilter(searchInput.text)
        }
    }

    private fun selectLessonTheme(lessonThemeName: String) {
        setEffect {
            HomeContract.Effect.Navigation.ToLessonThemeDetails(lessonThemeName)
        }
    }

    //TODO: This will be changed when we will integrate the app with the backend
    private fun applyFilter(searchInput: String) {
        if (searchInput.isBlank()) {
            setState { copy(lessonThemes = allLessonThemes) }
        } else {
            val randomNumberOfLessonThemes = Random.nextInt(1, allLessonThemes.size)
            val someLessonThemes = mutableListOf<LessonTheme>()
            repeat(randomNumberOfLessonThemes) {
                someLessonThemes.add(allLessonThemes[Random.nextInt(0, allLessonThemes.size)])
            }
            setState { copy(lessonThemes = someLessonThemes) }
        }
    }
}