package com.teacherworkout.features.discover.landing

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ViewModel(
    private val themesRepository: ThemesRepository
): BaseViewModel<Contract.Event, Contract.State, Contract.Effect>() {

    private var allLessonThemes: List<Theme> = emptyList()

    override fun setInitialState(): Contract.State = Contract.State()

    override fun handleEvents(event: Contract.Event) {
        when(event) {
            is Contract.Event.SetSearchInput -> setSearchInput(event.searchInput)
            is Contract.Event.SelectLessonTheme -> selectLessonTheme(event.lessonThemeId)
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
                false -> Log.e("DiscoveryViewModel", result.exceptionOrNull()?.message ?: "")
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
            Contract.Effect.Navigation.ToLessonThemeDetails(lessonThemeId)
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
