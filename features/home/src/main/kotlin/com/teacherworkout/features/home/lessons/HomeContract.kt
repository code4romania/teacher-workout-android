package com.teacherworkout.features.home.lessons

import androidx.compose.ui.text.input.TextFieldValue
import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState
import com.teacherworkout.commons.ui.model.LessonTheme

class HomeContract {
    sealed class Event : ViewEvent {
        data class SetSearchInput(val searchInput: TextFieldValue) : Event()
        data class SelectLessonTheme(val lessonThemeName: String) : Event()
    }

    data class State(
        val searchInput: TextFieldValue = TextFieldValue(),
        val lessonThemes: List<LessonTheme> = emptyList(),
        val isLoading: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToLessonThemeDetails(val lessonThemeName: String) : Navigation()
        }
    }
}