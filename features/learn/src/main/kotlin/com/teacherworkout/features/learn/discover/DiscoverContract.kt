package com.teacherworkout.features.learn.discover

import androidx.compose.ui.text.input.TextFieldValue
import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState
import com.teacherworkout.features.learn.data.LessonTheme

class DiscoverContract {
    sealed class Event : ViewEvent {
        data class SetSearchInput(val searchInput: TextFieldValue) : Event()
        data class SelectLessonTheme(val lessonName: String): Event()
    }

    data class State(
        val searchInput: TextFieldValue = TextFieldValue(),
        val lessonThemes: List<LessonTheme> = emptyList(),
        val isLoading: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation: Effect() {
            data class ToLessonThemeDetails(val lessonThemeName: String): Navigation()
        }
    }
}