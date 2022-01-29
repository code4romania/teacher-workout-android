package com.teacherworkout.features.learn.discover

import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.commons.ui.data.LessonsRepository
import com.teacherworkout.commons.ui.model.LessonTheme
import com.teacherworkout.commons.ui.data.Result
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class DiscoverViewModel(
    private val lessonsRepository: LessonsRepository
): BaseViewModel<DiscoverContract.Event, DiscoverContract.State, DiscoverContract.Effect>() {

    private var allLessonThemes: List<LessonTheme> = emptyList()

    override fun setInitialState(): DiscoverContract.State = DiscoverContract.State()

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
            val result = lessonsRepository.getAllLessonThemes()
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

    private fun selectLessonTheme(lessonThemeId: Long) {
        setEffect {
            DiscoverContract.Effect.Navigation.ToLessonThemeDetails(lessonThemeId)
        }
    }

    private fun applyFilter(searchInput: String) {
        if (searchInput.isBlank()) {
             setState{ copy(lessonThemes = allLessonThemes) }
        } else {
            setState{ copy(lessonThemes = allLessonThemes.filter { it.title.lowercase().contains(searchInput.lowercase()) }) }
        }
    }
}
