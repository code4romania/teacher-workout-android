package com.teacherworkout.features.learn.discover

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.core.data.repositories.LessonsRepository
import com.teacherworkout.core.data.Result
import com.teacherworkout.core.fragment.LessonTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class DiscoverViewModel(
    private val lessonsRepository: LessonsRepository
): BaseViewModel<DiscoverContract.Event, DiscoverContract.State, DiscoverContract.Effect>() {

    private var allLessonThemes: List<LessonTheme> = emptyList()

    override fun setInitialState(): DiscoverContract.State {
        return DiscoverContract.State()
    }

    override fun handleEvents(event: DiscoverContract.Event) {
        when(event) {
            is DiscoverContract.Event.SetSearchInput -> setSearchInput(event.searchInput)
            is DiscoverContract.Event.SelectLessonTheme -> selectLessonTheme(event.lessonThemeId)
        }
    }

    init {
        refreshLessonThemes()
    }

    private fun refreshLessonThemes() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            val result = lessonsRepository.getLessonThemes()
            setState { copy(isLoading = false) }
            when(result) {
                is Result.Success -> {
                    allLessonThemes = result.data
                    setState { copy(searchInput = TextFieldValue(), lessonThemes = allLessonThemes) }
                }
                is Result.Error -> {

                }
            }
        }
    }

    private fun setSearchInput(searchInput: TextFieldValue) {
        val prevSearchInput = viewState.value.searchInput
        setState { copy(searchInput = searchInput) }
        if(prevSearchInput.text != searchInput.text) {
            applyFilter(searchInput.text)
        }
    }

    private fun selectLessonTheme(lessonThemeId: String) {
        setEffect {
            DiscoverContract.Effect.Navigation.ToLessonThemeDetails(lessonThemeId)
        }
    }

    //TODO: This will be changed when we will integrate the app with the backend
    private fun applyFilter(searchInput: String) {
        if (searchInput.isBlank()) {
             setState{ copy(lessonThemes = allLessonThemes) }
        } else {
            val randomNumberOfLessonThemes = Random.nextInt(1, allLessonThemes.size)
            val someLessonThemes = mutableListOf<LessonTheme>()
            repeat(randomNumberOfLessonThemes) {
                someLessonThemes.add(allLessonThemes[Random.nextInt(0, allLessonThemes.size)])
            }
            setState{ copy(lessonThemes = someLessonThemes) }
        }
    }
}
