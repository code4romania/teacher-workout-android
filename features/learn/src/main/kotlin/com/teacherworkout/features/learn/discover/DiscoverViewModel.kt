package com.teacherworkout.features.learn.discover

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class DiscoverViewModel(
    private val themesRepository: ThemesRepository
): BaseViewModel<DiscoverContract.Event, DiscoverContract.State, DiscoverContract.Effect>() {

    private var allLessonThemes: List<Theme> = emptyList()

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
            val result = themesRepository.all()
            setState { copy(isLoading = false) }
            when(result.isSuccess) {
                true -> {
                    allLessonThemes = result.getOrDefault(emptyList())
                    setState { copy(searchInput = TextFieldValue(), themes = allLessonThemes) }
                }
                false -> {
                    Log.e("DiscoveryViewModel", result.exceptionOrNull()?.message ?: "")
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

    private fun applyFilter(searchInput: String) {
        if (searchInput.isBlank()) {
             setState{ copy(themes = allLessonThemes) }
        } else {
            setState{ copy(themes = allLessonThemes.filter { it.title.lowercase().contains(searchInput.lowercase()) }) }
        }
    }
}
